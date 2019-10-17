package co.edu.usbcali.bank.jpa;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;


class ClienteJpaTest {

	private final static Long clieId=4560L;
	
	EntityManagerFactory entityManagerFactory=null;
	EntityManager entityManager=null;
	
	@BeforeEach
	void beforeEach(){
		entityManagerFactory=Persistence.createEntityManagerFactory("bank-logic");
		entityManager=entityManagerFactory.createEntityManager();
	}
	
	@AfterEach
	void after(){
	 entityManager.close();
	 entityManagerFactory.close();		
	}
	
	@Test
	@DisplayName("save")
	void aTest() {
	   assertNotNull(entityManager,"El entityManager es nulo");
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
	   
	   
	   entityManager.getTransaction().begin();
	   entityManager.persist(cliente);
	   entityManager.getTransaction().commit();
	   
	}
	
	@Test
	@DisplayName("findById")
	void bTest() {
	   assertNotNull(entityManager,"El entityManager es nulo");
	   Cliente cliente=entityManager.find(Cliente.class, clieId);
	   assertNotNull(cliente,"El cliente con id:"+clieId+"No existe");
   
	}	
	
	
	@Test
	@DisplayName("update")
	void cTest() {
	   assertNotNull(entityManager,"El entityManager es nulo");
	   Cliente cliente=entityManager.find(Cliente.class, clieId);
	   assertNotNull(cliente,"El cliente con id:"+clieId+"No existe");
	   
	   cliente.setActivo("N");
	   
	   entityManager.getTransaction().begin();
	   entityManager.merge(cliente);
	   entityManager.getTransaction().commit();	   
	}	
	
	@Test
	@DisplayName("delete")
	void dTest() {
	   assertNotNull(entityManager,"El entityManager es nulo");
	   Cliente cliente=entityManager.find(Cliente.class, clieId);
	   assertNotNull(cliente,"El cliente con id:"+clieId+"No existe");
	   
	   entityManager.getTransaction().begin();
	   entityManager.remove(cliente);
	   entityManager.getTransaction().commit();	   
	}	
	
	private final static Logger log=LoggerFactory.getLogger(ClienteJpaTest.class);
	
	@Test
	@DisplayName("findAll")
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

