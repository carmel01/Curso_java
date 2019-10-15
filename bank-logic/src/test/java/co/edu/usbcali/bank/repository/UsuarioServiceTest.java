package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.service.UsuarioService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class UsuarioServiceTest {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;

	private final static String usu_usuario = "jfiayo";
	private final static Logger log = LoggerFactory.getLogger(UsuarioServiceTest.class);

	@Test
	@DisplayName("save")
	void aTest() {

		assertNotNull(usuarioService, "usuarioService nulo");
		assertFalse(usuarioService.findById(usu_usuario).isPresent(), "usuario existe");
		Usuario usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setClave("pasword");
		usuario.setNombre("Fiayiño");
		usuario.setUsuUsuario(usu_usuario);
		usuario.setIdentificacion(new BigDecimal(12345));
		assertTrue(tipoUsuarioRepository.findById(1L).isPresent(), "Tipo usuario no existe");
		usuario.setTipoUsuario(tipoUsuarioRepository.findById(1L).get());
		try {
			usuarioService.save(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	@DisplayName("finById")
	void bTest() {

		assertNotNull(usuarioService, "usuarioService nulo");
		Usuario usuario = usuarioService.findById(usu_usuario).get();
		assertNotNull(usuario, "Usiario con Id:" + usu_usuario + " No existe");

	}

	@Test
	@DisplayName("update")
	void cTest() {

		assertNotNull(usuarioService, "usuarioService nulo");
		Usuario usuario = usuarioService.findById(usu_usuario).get();
		assertNotNull(usuario, "Usiario con Id:" + usu_usuario + " No existe");

		usuario.setActivo("N");

		try {
			usuarioService.update(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void dTest() {

		assertNotNull(usuarioService, "usuarioService nulo");
		Usuario usuario = usuarioService.findById(usu_usuario).get();
		assertNotNull(usuario, "Usiario con Id:" + usu_usuario + " No existe");

		try {
			usuarioService.delete(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
