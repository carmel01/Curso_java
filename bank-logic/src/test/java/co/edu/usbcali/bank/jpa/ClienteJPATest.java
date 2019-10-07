package co.edu.usbcali.bank.jpa;

import static org.junit.jupiter.api.Assertions.*;

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

class ClienteJPATest {

	private final static Long clieId = 4560L;
	private final static Logger log=LoggerFactory.getLogger(ClienteJPATest.class);
	
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;

	@BeforeEach
	void beforeEach() {
		entityManagerFactory = Persistence.createEntityManagerFactory("bank-logic");
		entityManager = entityManagerFactory.createEntityManager();

	}

	@AfterEach
	void afterEach() {
		entityManagerFactory.close();
		entityManager.close();

	}

	@Test
	@DisplayName("save")
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
		cliente.setTelefono("32756756751");

		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, 1L);
		assertNotNull(tipoDocumento, "Tipo documento no existe");
		cliente.setTipoDocumento(tipoDocumento);
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
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
	void cTest() {

		assertNotNull(entityManager, "EL entityManager NULL");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "Cliente con Id:" + clieId + " No existe");

		cliente.setActivo("N");

		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();
		

	}

	@Test
	@DisplayName("delete")
	void dTest() {

		assertNotNull(entityManager, "EL entityManager NULL");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "Cliente con Id:" + clieId + " No existe");

		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
		

	}
	

	
	
	@Test
	@DisplayName("list")
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
