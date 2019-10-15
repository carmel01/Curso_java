package co.edu.usbcali.bank.spring;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
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


import co.edu.usbcali.bank.domain.TipoUsuario;
import co.edu.usbcali.bank.domain.Usuario;



@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class UsuarioSpringTest {

	private final static String usu_usuario = "jfiayo";
	private final static Logger log=LoggerFactory.getLogger(UsuarioSpringTest.class);
	
	
	@PersistenceContext
	EntityManager entityManager;

	@Test
	@DisplayName("save")
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
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
		entityManager.persist(usuario);
				
	}
	@Test
	@DisplayName("finById")
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	void bTest() {

		assertNotNull(entityManager, "EL entityManager NULL");
		Usuario usuario= entityManager.find(Usuario.class, usu_usuario);
		assertNotNull(usuario, "Usiario con Id:" + usu_usuario + " No existe");

	}

	@Test
	@DisplayName("update")
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	void cTest() {

		assertNotNull(entityManager, "EL entityManager NULL");
		Usuario usuario= entityManager.find(Usuario.class, usu_usuario);
		assertNotNull(usuario, "Usuario con Id:" + usu_usuario + " No existe");

		usuario.setActivo("N");

		entityManager.merge(usuario);
		
		

	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	void dTest() {

		assertNotNull(entityManager, "EL entityManager NULL");
		Usuario usuario= entityManager.find(Usuario.class, usu_usuario);
		assertNotNull(usuario, "Usuario con Id:" + usu_usuario + " No existe");

	
		entityManager.remove(usuario);
		
		

	}
	

	
	
	@Test
	@DisplayName("list")
	@Transactional(readOnly = true)
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
