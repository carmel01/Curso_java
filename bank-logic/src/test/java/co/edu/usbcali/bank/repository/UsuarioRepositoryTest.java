package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
class UsuarioRepositoryTest {

	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;

	private final static Long idTusu = 1L;
	private final static String usu_usuario = "jfiayo";
	private final static Logger log = LoggerFactory.getLogger(UsuarioRepositoryTest.class);

	@Test
	@DisplayName("save")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void aTest() {

		assertNotNull(usuarioRepository);
		assertNotNull(tipoUsuarioRepository);

		assertFalse(usuarioRepository.findById(usu_usuario).isPresent());

		Usuario usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setClave("jfiayo");
		usuario.setNombre("Fiayiño");
		usuario.setUsuUsuario(usu_usuario);
		usuario.setIdentificacion(new BigDecimal(12345));

		
		assertTrue(tipoUsuarioRepository.findById(idTusu).isPresent(), "No existe TipoUsuario");
		usuario.setTipoUsuario(tipoUsuarioRepository.findById(idTusu).get());

		usuarioRepository.save(usuario);

	}

	@Test
	@DisplayName("finById")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void bTest() {

		
		assertNotNull(usuarioRepository);
		assertTrue(usuarioRepository.findById(usu_usuario).isPresent(), "el usuario no existe");
		Usuario usuario = usuarioRepository.findById(usu_usuario).get();
		assertNotNull(usuario, "Usiario con Id:" + usu_usuario + " No existe");

	}

	@Test
	@DisplayName("update")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void cTest() {

		
		assertNotNull(usuarioRepository);

		assertTrue(usuarioRepository.findById(usu_usuario).isPresent(), "el usuario no existe");
		Usuario usuario = usuarioRepository.findById(usu_usuario).get();

		usuario.setActivo("N");

		usuarioRepository.save(usuario);

	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void dTest() {

	
		assertNotNull(usuarioRepository);

		assertTrue(usuarioRepository.findById(usu_usuario).isPresent(), "el usuario no existe");
		Usuario usuario = usuarioRepository.findById(usu_usuario).get();
		usuarioRepository.delete(usuario);

	}

	@Test
	@DisplayName("listAll")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void eTest() {

		
		assertNotNull(usuarioRepository);

		List<Usuario> losUsuarios = usuarioRepository.findAll();

		assertNotNull(losUsuarios);
		assertFalse(losUsuarios.isEmpty(), "No hay usuarios");

		for (Usuario usuario : losUsuarios) {
			log.info(usuario.getNombre());

		}

		losUsuarios.forEach(t -> {
			log.info(t.getUsuCreador());
		});

		losUsuarios.forEach(t -> log.info(t.getUsuUsuario()));

	}

}
