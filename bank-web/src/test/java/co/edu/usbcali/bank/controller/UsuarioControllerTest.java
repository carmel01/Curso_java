package co.edu.usbcali.bank.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.dto.UsuarioDTO;;

class UsuarioControllerTest {
	
	private final static String usuUsuario="Alan Brito";
	private final static Logger log=LoggerFactory.getLogger(UsuarioControllerTest.class);	
	
    private final static String url="http://localhost:8080/bank-web/api/usuario/";
	
	@Test
	@DisplayName("save")
	void aTest() {
		RestTemplate restTemplate= new RestTemplate();
		
		UsuarioDTO usuarioDto= new UsuarioDTO();
		usuarioDto.setActivo("S");
		usuarioDto.setClave("12345678");
		usuarioDto.setIdentificacion(new BigDecimal(12345678));
		usuarioDto.setNombre("Maximiliano Williams");
		usuarioDto.setTiusId(1L);
		usuarioDto.setUsuUsuario(usuUsuario);		
		
		Object resultado=restTemplate.postForObject(url+"save", usuarioDto, Object.class);
		
		assertNotNull(resultado);
	}
	
	
	@Test
	@DisplayName("findById")
	void bTest() {
		RestTemplate restTemplate= new RestTemplate();
		UsuarioDTO resultado=restTemplate.getForObject(url+"findById/"+usuUsuario,UsuarioDTO.class);
		assertNotNull(resultado,"El usuario no existe");		
	}	
	
	@Test
	@DisplayName("update")
	void cTest()
	{
		RestTemplate restTemplate= new RestTemplate();
		
		UsuarioDTO usuarioDto= new UsuarioDTO();
		usuarioDto.setActivo("S");
		usuarioDto.setClave("12345678");
		usuarioDto.setIdentificacion(new BigDecimal(12345678));
		usuarioDto.setNombre("Maximiliano Williams");
		usuarioDto.setTiusId(1L);
		usuarioDto.setUsuUsuario(usuUsuario);			
	
		restTemplate.put(url+"update", usuarioDto);
    }
	
	@Test
	@DisplayName("delete")
	void dTest() {
		RestTemplate restTemplate= new RestTemplate();
		restTemplate.delete(url+"delete/"+usuUsuario);		
	}		

}
