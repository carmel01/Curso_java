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

	private final static Long clieId = 4560L;
	private final static Logger log=LoggerFactory.getLogger(ClienteSpringTest.class);
	
	
	@PersistenceContext
	EntityManager entityManager;

	@Test
	@DisplayName("insert")
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	void aTest() {

		assertNotNull(entityManager, "EL entityManager NULL");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNull(cliente, "Cliente con Id:" + clieId + " ya existe");
		cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setEmail("j@gmail.com");
		cliente.setDireccion("uni san buenaventura");
		cliente.setNombre("Fiayiño");
		cliente.setTelefono("321");

		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, 1L);
		assertNotNull(tipoDocumento, "Tipo documento no existe");
		cliente.setTipoDocumento(tipoDocumento);
	    entityManager.persist(cliente);
		
		
	}
	
	
	@Test
	@DisplayName("finById")
	void bTest() {

		assertNotNull(entityManager, "EL entityManager NULL");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "Cliente con Id:" + clieId + " No existe");

	}

	@Test
	@DisplayName("update")
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	void cTest() {

		assertNotNull(entityManager, "EL entityManager NULL");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "Cliente con Id:" + clieId + " No existe");
		cliente.setActivo("N");
		entityManager.merge(cliente);
		
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	void dTest() {

		assertNotNull(entityManager, "EL entityManager NULL");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "Cliente con Id:" + clieId + " No existe");
		entityManager.remove(cliente);
		
	}
	

	
	
	@Test
	@DisplayName("list")
	@Transactional(readOnly = true)
	void eTest() {

		assertNotNull(entityManager, "EL entityManager NULL");
		String jpql= "SELECT cli FROM Cliente cli";
		Query query= entityManager.createQuery(jpql);
		List<Cliente> losClientes=query.getResultList();
		assertNotNull(losClientes);
		assertFalse(losClientes.isEmpty());
		
     	for (Cliente cliente : losClientes) {
     		log.info(cliente.getNombre());
			
		}
     	
     	losClientes.forEach(cliente->{
     		log.info(cliente.getClieId().toString());     		
     	});
	}

}
