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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClienteJPATest {
	private final static long clieId = 4560L;
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;

	@BeforeEach
	void before() {
		entityManagerFactory = Persistence.createEntityManagerFactory("bank-logic");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@AfterEach
	void after() {
		entityManagerFactory.close();
		entityManager.close();
	}

	@Test
	@Order(1)
	@DisplayName("save")
	void test() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNull(cliente, "El cliente con id " + clieId + " ya existe");

		cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida siempre viva 123");
		cliente.setEmail("HomeroJSimpson@gmail.com");
		cliente.setNombre("Homer Jay Simpson");
		cliente.setTelefono("555 555 555");

		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, 1L);
		assertNotNull(tipoDocumento, "El tipo de documento con id: 1 ya existe");
		cliente.setTipoDocumento(tipoDocumento);

		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();

	}

	@Test
	@Order(2)
	@DisplayName("findById")
	void btest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente con id " + clieId + " no existe");

	}

	@Test
	@Order(3)
	@DisplayName("update")
	void ctest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente con id " + clieId + " no existe");

		cliente.setActivo("S");
		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();
	}

	@Test
	@Order(4)
	@DisplayName("delete")
	void dtest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente con id " + clieId + " no existe");

		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
	}

	private final static Logger log = LoggerFactory.getLogger(ClienteJPATest.class);

	@Test
	@Order(5)
	@DisplayName("findAll")
	void eTest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		String jpql="SELECT cli FROM Cliente cli";
		Query query=entityManager.createQuery(jpql);
		List<Cliente> clientes=query.getResultList();
		assertNotNull(clientes);
		assertFalse(clientes.isEmpty());
		
		for (Cliente cliente : clientes) {
			log.info(cliente.getNombre());			
		}
		
		clientes.forEach(cliente->{
			log.info(cliente.getClieId().toString());
			});
}

}
