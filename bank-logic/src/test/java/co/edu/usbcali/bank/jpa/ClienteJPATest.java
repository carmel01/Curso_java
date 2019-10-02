package co.edu.usbcali.bank.jpa;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;

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
	@DisplayName("findById")
	void btest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente con id " + clieId + " no existe");

	}

	@Test
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
	@DisplayName("delete")
	void dtest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente con id " + clieId + " no existe");

		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
	}

}
