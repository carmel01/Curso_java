package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.service.UsuarioService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class UsuarioServiceTest {

	private final static String usu_usuario = "cenavia";
	private final static Logger log = LoggerFactory.getLogger(UsuarioServiceTest.class);

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;

	@Test
	@DisplayName("save")
	void aTest() {

		assertNotNull(usuarioService, "El usuarioService NULL");
		assertNotNull(tipoUsuarioRepository, "El tipoUsuarioRepository NULL");
		Usuario usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setClave("cenavia123");
		usuario.setNombre("carlitos guey");
		usuario.setUsuUsuario(usu_usuario);
		usuario.setIdentificacion(new BigDecimal(12345));
		assertTrue(tipoUsuarioRepository.findById(1L).isPresent(), "el tipo de usuario no existe");
		usuario.setTipoUsuario(tipoUsuarioRepository.findById(1L).get());

		try {
			usuarioService.save(usuario);

		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

	@Test
	@DisplayName("finById")
	void bTest() {

		assertNotNull(usuarioService, "El usuarioService NULL");
		Usuario usuario = usuarioService.findById(usu_usuario).get();
		assertNotNull(usuario, "Usuario con Id:" + usu_usuario + " No existe");

	}

	@Test
	@DisplayName("update")
	void cTest() {

		assertNotNull(usuarioService, "El usuarioService NULL");
		Usuario usuario = usuarioService.findById(usu_usuario).get();
		assertNotNull(usuario, "Usuario con Id:" + usu_usuario + " No existe");

		usuario.setActivo("N");

		try {
			usuarioService.update(usuario);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

	@Test
	@DisplayName("delete")
	void dTest() {

		assertNotNull(usuarioService, "El usuarioService NULL");
		Usuario usuario = usuarioService.findById(usu_usuario).get();
		assertNotNull(usuario, "Usuario con Id:" + usu_usuario + " No existe");

		try {
			usuarioService.delete(usuario);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}

	}
}
