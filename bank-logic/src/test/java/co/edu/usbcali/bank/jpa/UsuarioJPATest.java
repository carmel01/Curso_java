package co.edu.usbcali.bank.jpa;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
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
import co.edu.usbcali.bank.domain.TipoUsuario;
import co.edu.usbcali.bank.domain.Usuario;

class UsuarioJPATest {

	private final static String usu_usuario = "jfiayo";
	private final static Logger log=LoggerFactory.getLogger(UsuarioJPATest.class);
	
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
		Usuario usuario= entityManager.find(Usuario.class, usu_usuario);
		assertNull(usuario, "Usuario con Id:" + usu_usuario + " ya existe");
		usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setClave("jfiayo");
		usuario.setNombre("Fiayiño");
		usuario.setUsuUsuario(usu_usuario);
		usuario.setIdentificacion(new BigDecimal(12345));
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, 1L);
		assertNotNull(tipoUsuario, "Tipo usuario no existe");
		usuario.setTipoUsuario(tipoUsuario);
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		
	}
	@Test
	@DisplayName("finById")
	void bTest() {

		assertNotNull(entityManager, "EL entityManager NULL");
		Usuario usuario= entityManager.find(Usuario.class, usu_usuario);
		assertNotNull(usuario, "Usiario con Id:" + usu_usuario + " No existe");

	}

	@Test
	@DisplayName("update")
	void cTest() {

		assertNotNull(entityManager, "EL entityManager NULL");
		Usuario usuario= entityManager.find(Usuario.class, usu_usuario);
		assertNotNull(usuario, "Usuario con Id:" + usu_usuario + " No existe");

		usuario.setActivo("N");

		entityManager.getTransaction().begin();
		entityManager.merge(usuario);
		entityManager.getTransaction().commit();
		

	}

	@Test
	@DisplayName("delete")
	void dTest() {

		assertNotNull(entityManager, "EL entityManager NULL");
		Usuario usuario= entityManager.find(Usuario.class, usu_usuario);
		assertNotNull(usuario, "Usuario con Id:" + usu_usuario + " No existe");

		entityManager.getTransaction().begin();
		entityManager.remove(usuario);
		entityManager.getTransaction().commit();
		

	}
	

	
	
	@Test
	@DisplayName("list")
	void eTest() {

		assertNotNull(entityManager, "EL entityManager NULL");
		String jpql= "SELECT usu FROM Usuario usu";
		Query query= entityManager.createQuery(jpql);
		List<Usuario> losUsuarios=query.getResultList();
		assertNotNull(losUsuarios);
		assertFalse(losUsuarios.isEmpty());
		
     	for (Usuario usuario : losUsuarios) {
     		log.info(usuario.getNombre());
			
		}
     	
     	losUsuarios.forEach(usuario->{
     		log.info(usuario.getIdentificacion().toString());     		
     	});
	}


}
