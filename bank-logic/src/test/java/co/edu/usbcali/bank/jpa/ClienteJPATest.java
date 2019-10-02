package co.edu.usbcali.bank.jpa;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteJPATest {

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
	void test() {
		assertNotNull(entityManager, "El entityManager es nulo");
	}

}
