package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.repository.TipoUsuarioRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class UsuarioServiceTest {
	
	private final static String usuUsuario="Alan Brito";
	
	private final static Logger log=LoggerFactory.getLogger(UsuarioServiceTest.class);
    @Autowired
    UsuarioService usuarioService;  
    
    @Autowired
    TipoUsuarioRepository tipoUsuarioRepository;

	@Test
	@DisplayName("save")
	void aTest() {
       assertNotNull(usuarioService); 
       assertNotNull(tipoUsuarioRepository);
		
       Usuario usuario = new Usuario();
       usuario.setActivo("S");
	   usuario.setClave("12345678");
	   usuario.setIdentificacion(new BigDecimal(12345678));
	   usuario.setNombre("Maximiliano Williams");
	   assertTrue(tipoUsuarioRepository.findById(1L).isPresent(),"El tipo de documento no existe");	   
	   usuario.setTipoUsuario(tipoUsuarioRepository.findById(1L).get());
	   usuario.setUsuUsuario(usuUsuario);
         
		try {
			usuarioService.save(usuario);
		} catch (Exception e) {
			assertNull(e,e.getMessage());
		}
	}
	
	@Test
	@DisplayName("findById")
	void bTest() {
		 assertNotNull(usuarioService, "El usuario es nulo");
		 Optional<Usuario> usuarioOpcional=usuarioService.findById(usuUsuario); 
		 assertTrue(usuarioOpcional.isPresent());
	}
	
	@Test
	@DisplayName("update")
	void cTest() {
		assertNotNull(usuarioService,"El repositorio es nulo");
		Optional<Usuario> usuarioOpcional=usuarioService.findById(usuUsuario);
		assertTrue(usuarioOpcional.isPresent(),"El usuario no existe");
		Usuario usuario=usuarioOpcional.get();
		usuario.setActivo("N");
		
		try {
			usuarioService.update(usuario);
		} catch (Exception e) {
			assertNull(e,e.getMessage());
		}		
    }
	
	@Test
	@DisplayName("Delete")
	void dtest()
	{
		assertNotNull(usuarioService,"El repositorio es nulo");
		Optional<Usuario> usuarioOpcional=usuarioService.findById(usuUsuario);
		assertTrue(usuarioOpcional.isPresent());
		Usuario usuario=usuarioOpcional.get();
		try {
			usuarioService.delete(usuario);	
		} catch (Exception e) {
			assertNull(e,e.getMessage());
		}
			
	}	
	

}
