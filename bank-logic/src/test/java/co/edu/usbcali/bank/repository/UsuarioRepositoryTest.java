package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Usuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class UsuarioRepositoryTest {
	private final static String usuUsuario="Alan Brito";
	
	private final static Logger log=LoggerFactory.getLogger(UsuarioRepositoryTest.class);
    
    @Autowired
    UsuarioRepository usuarioRepository;   

    @Autowired
    TipoUsuarioRepository tipoUsuarioRepository;
    
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void aTest() {
		assertNotNull(usuarioRepository,"El usuario es nulo");
		assertNotNull(tipoUsuarioRepository);
        assertFalse(usuarioRepository.findById(usuUsuario).isPresent(),"El usuario ya existe");
		   
	    Usuario usuario= new Usuario();
	    usuario.setUsuUsuario(usuUsuario);
	    usuario.setActivo("Y");
	    usuario.setClave("12345");	
	    usuario.setIdentificacion(new BigDecimal (10290000));	
	    usuario.setNombre("Max Power");					
	    assertTrue(tipoUsuarioRepository.findById(1L).isPresent(),"El tipo de documento no existe");
	    usuario.setTipoUsuario(tipoUsuarioRepository.findById(1L).get());
	    usuarioRepository.save(usuario);
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {
		assertNotNull(usuarioRepository,"El Usuario es nulo");
		Optional<Usuario> usuarioOptional= usuarioRepository.findById(usuUsuario);
		assertTrue(usuarioOptional.isPresent());		
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED )
	void cTest()
	{
		assertNotNull(usuarioRepository,"El repositorio es nulo");
		Optional<Usuario> UsuarioOptional=usuarioRepository.findById(usuUsuario);
		assertTrue(UsuarioOptional.isPresent());
		Usuario usuario=UsuarioOptional.get();
		usuario.setActivo("N");
			
		usuarioRepository.save(usuario);		
		
    }
	
	
	@Test
	@DisplayName("Delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED )
	void dtest()
	{
		assertNotNull(usuarioRepository,"El repositorio es nulo");
		Optional<Usuario> UsuarioOptional=usuarioRepository.findById(usuUsuario);
		assertTrue(UsuarioOptional.isPresent());
		Usuario usuario=UsuarioOptional.get();
		usuarioRepository.delete(usuario);		
	}	
	
	
	@Test
	@DisplayName("Find All")
	@Transactional(readOnly = true)
	void etest()
	{
		assertNotNull(usuarioRepository,"El repositorio es nulo");
		List<Usuario> usuarios=usuarioRepository.findAll();
		assertNotNull(usuarios);
		assertFalse(usuarios.isEmpty());
		
		usuarios.forEach(cliente->{log.info(cliente.getNombre());});
	}
	
	
	

	
}
