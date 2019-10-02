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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.domain.TipoUsuario;
import co.edu.usbcali.bank.domain.Usuario;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioJPATest {
	private final static String usu_usuario = "carmel";
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
		Usuario usuario= entityManager.find(Usuario.class, usu_usuario);
		assertNull(usuario, "El usuario con id " + usu_usuario + " ya existe");

		usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setUsuUsuario(usu_usuario);
		usuario.setNombre("Homer Jay Simpson");
		usuario.setClave("carlitos");
		usuario.setIdentificacion(new BigDecimal(6734));	
		
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, 1L);
		assertNotNull(tipoUsuario, "El tipo de usuario con id: 1 ya existe");
		usuario.setTipoUsuario(tipoUsuario);

		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();

	}

	@Test
	@Order(2)
	@DisplayName("findById")
	void btest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Usuario usuario= entityManager.find(Usuario.class, usu_usuario);
		assertNotNull(usuario, "El usuario con id " + usu_usuario + " no existe");

	}

	@Test
	@Order(3)
	@DisplayName("update")
	void ctest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Usuario usuario = entityManager.find(Usuario.class, usu_usuario);
		assertNotNull(usuario, "El usuario con id " + usu_usuario + " no existe");

		usuario.setActivo("N");
		entityManager.getTransaction().begin();
		entityManager.merge(usuario);
		entityManager.getTransaction().commit();
	}

	@Test
	@Order(4)
	@DisplayName("delete")
	void dtest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Usuario usuario = entityManager.find(Usuario.class, usu_usuario);
		assertNotNull(usuario, "El usuario con id " + usu_usuario + " no existe");

		entityManager.getTransaction().begin();
		entityManager.remove(usuario);
		entityManager.getTransaction().commit();
	}

	private final static Logger log = LoggerFactory.getLogger(UsuarioJPATest.class);

	@Test
	@Order(5)
	@DisplayName("findAll")
	void eTest() {
		assertNotNull(entityManager, "El entityManager es nulo");
		String jpql="FROM Usuario";
		Query query=entityManager.createQuery(jpql);
		List<Usuario> usuarios=query.getResultList();
		assertNotNull(usuarios);
		assertFalse(usuarios.isEmpty());
		
		for (Usuario usuario : usuarios) {
			log.info(usuario.getNombre());			
		}
		
		usuarios.forEach(usuario->{
			log.info(usuario.getIdentificacion().toString());
			});
}

}
