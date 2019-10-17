package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.repository.TipoUsuarioRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class UsuarioServiceTest {

	private final static String usuUsuario= "ahah";
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;
	
	@Test
	@DisplayName("save")
	void atest() {
		assertNotNull(usuarioService);
		assertNotNull(tipoUsuarioRepository);
		
		Usuario usuario=new Usuario();
		usuario.setActivo("S");
		usuario.setClave("s5s45");
		usuario.setIdentificacion(new BigDecimal(5545));
		usuario.setNombre("andres");
		usuario.setUsuUsuario(usuUsuario);
		
		assertTrue(tipoUsuarioRepository.findById(1L).isPresent(),"El tipo usuario no existe");
		
		usuario.setTipoUsuario(tipoUsuarioRepository.findById(1L).get());
		
		try {
			usuarioService.save(usuario);
		} catch (Exception e) {
			assertNull(e,e.getMessage());
		}
	}
	
	@Test
	@DisplayName("findById")
	void bTest() {
		assertNotNull(usuarioService,"usuarioService es nulo");
		Optional<Usuario> usuarioOptional=usuarioService.findById(usuUsuario);
		assertTrue(usuarioOptional.isPresent());
	}
	
	@Test
	@DisplayName("update")
	@Transactional
	void cTest() {
		assertNotNull(usuarioService,"usuarioService es nulo");
		Optional<Usuario> usuarioOptional= usuarioService.findById(usuUsuario);
		assertTrue(usuarioOptional.isPresent(),"el usuario no existe");
		
		Usuario usuario = usuarioOptional.get();
		usuario.setActivo("N");
		
		try {
			usuarioService.update(usuario);
		} catch (Exception e) {		
			assertNull(e,e.getMessage());
		}
	
	}
	
	@Test
	@DisplayName("delete")
	void dTest() {
		assertNotNull(usuarioService,"usuarioService es nulo");
		Optional<Usuario> usuarioOptional= usuarioService.findById(usuUsuario);
		assertTrue(usuarioOptional.isPresent(),"el usuario no existe");
		
		Usuario usuario=usuarioOptional.get();
		try {
			usuarioService.delete(usuario);
		} catch (Exception e) {
			assertNull(e,e.getMessage());
		}
	
	}

}
