package co.edu.usbcali.bank.spring;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class ClienteSpringTest {

	private final static Long clieId=450L;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void test() {
	 assertNotNull(entityManager,"El entityManager es nulo);");
	   Cliente cliente=entityManager.find(Cliente.class, clieId);
	   assertNull(cliente,"El cliente con id:"+clieId+"Ya existe");
	   
	   cliente= new Cliente();
	   cliente.setActivo("S");
	   cliente.setClieId(clieId);
	   cliente.setDireccion("Avenida Siempre Viva 123");
	   cliente.setEmail("prueba@gmail.com");
	   cliente.setNombre("Max Power");
	   cliente.setTelefono("2332534");
	   
	   TipoDocumento tipoDocumento=entityManager.find(TipoDocumento.class,1L);
	   assertNotNull(tipoDocumento,"El tipo de documento con id:1 no existe");
	   cliente.setTipoDocumento(tipoDocumento);
	   
	   entityManager.persist(cliente);		 
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {
	   assertNotNull(entityManager,"El entityManager es nulo");
	   Cliente cliente=entityManager.find(Cliente.class, clieId);
	   assertNotNull(cliente,"El cliente con id:"+clieId+"No existe");
   
	}		
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	void cTest() {
	   assertNotNull(entityManager,"El entityManager es nulo");
	   Cliente cliente=entityManager.find(Cliente.class, clieId);
	   assertNotNull(cliente,"El cliente con id:"+clieId+"No existe");
	   
	   cliente.setActivo("N");
	   
	   entityManager.merge(cliente);
	}	
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	void dTest() {
	   assertNotNull(entityManager,"El entityManager es nulo");
	   Cliente cliente=entityManager.find(Cliente.class, clieId);
	   assertNotNull(cliente,"El cliente con id:"+clieId+"No existe");
	   
	   entityManager.remove(cliente);
   
	}		
	
	private final static Logger log=LoggerFactory.getLogger(ClienteSpringTest.class);
	
	@Test
	@DisplayName("findAll")
	@Transactional(readOnly = true)
	void eTest() {
	   assertNotNull(entityManager,"El entityManager es nulo");
       String jpql="SELECT cli FROM Cliente cli";
       Query query=entityManager.createQuery(jpql);
       List<Cliente> Clientes=query.getResultList();
       assertNotNull(Clientes);
       assertFalse(Clientes.isEmpty());
       
       Clientes.forEach(cliente->{
         log.info(cliente.getNombre());
       });
	}	

	
	
		

}
