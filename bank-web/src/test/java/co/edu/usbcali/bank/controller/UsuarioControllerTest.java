package co.edu.usbcali.bank.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.bank.dto.UsuarioDTO;

class UsuarioControllerTest {

	private final static String usu_usuario = "usuarioTt";
	private final static Logger log = LoggerFactory.getLogger(UsuarioControllerTest.class);
	private final static String url = "http://localhost:8080/bank-web/api/usuario/";

	@Test
	@DisplayName("save")
	void aTest() {
		RestTemplate restTemplate = new RestTemplate();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setActivo("S");
		usuarioDTO.setClave("pasword");
		usuarioDTO.setNombre("Fiayiño");
		usuarioDTO.setUsuUsuario(usu_usuario);
		usuarioDTO.setIdentificacion(new BigDecimal(12345));
		usuarioDTO.setTiusId(1L);
		
		Object resultado = restTemplate.postForObject(url + "save", usuarioDTO, Object.class);

		assertNotNull(resultado);
	}

	@Test
	@DisplayName("findById")
	void bTest() {
		RestTemplate restTemplate = new RestTemplate();
		UsuarioDTO usuarioDTO  = restTemplate.getForObject(url + "findById/" + usu_usuario, UsuarioDTO.class);
		assertNotNull(usuarioDTO, "El usuario " + usu_usuario + " no existe");
	}

	@Test
	@DisplayName("update")
	void cTest() {
		RestTemplate restTemplate = new RestTemplate();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setActivo("N");
		usuarioDTO.setClave("claveUs");
		usuarioDTO.setNombre("fiayoC");
		usuarioDTO.setUsuUsuario(usu_usuario);
		usuarioDTO.setIdentificacion(new BigDecimal(78945));
		usuarioDTO.setTiusId(1L);

		restTemplate.put(url + "update", usuarioDTO);
	}

	@Test
	@DisplayName("delete")
	void dTest() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(url + "delete/" + usu_usuario);
	}

}
