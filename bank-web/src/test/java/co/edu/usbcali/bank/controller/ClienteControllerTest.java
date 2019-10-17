package co.edu.usbcali.bank.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.dto.ClienteDTO;

class ClienteControllerTest {
	
	private final static Long clieId=9092L;
	private final static Logger log=LoggerFactory.getLogger(ClienteControllerTest.class);
    private final static String url="http://localhost:8080/bank-web/api/cliente/";
	
	@Test
	@DisplayName("save")
	void aTest() {
		RestTemplate restTemplate= new RestTemplate();
		ClienteDTO clienteDTO= new ClienteDTO();
		clienteDTO.setActivo("S");
		clienteDTO.setClieId(clieId);
		clienteDTO.setDireccion("Evergreen 123");
		clienteDTO.setEmail("Min.power@msn.com.co");
		clienteDTO.setNombre("Min Power");
		clienteDTO.setTdocId(1L);
		clienteDTO.setTelefono("12345678");
		Object resultado=restTemplate.postForObject(url+"save", clienteDTO, Object.class);
		
		assertNotNull(resultado);
	}
	
	
	@Test
	@DisplayName("findById")
	void bTest() {
		RestTemplate restTemplate= new RestTemplate();
		ClienteDTO resultado=restTemplate.getForObject(url+"findById/"+clieId,ClienteDTO.class);
		assertNotNull(resultado,"El cliente no existe");		
	}	
	
	@Test
	@DisplayName("update")
	void cTest()
	{
		RestTemplate restTemplate= new RestTemplate();
		ClienteDTO clienteDTO= new ClienteDTO();
		clienteDTO.setActivo("S");
		clienteDTO.setClieId(clieId);
		clienteDTO.setDireccion("Evergreen 123");
		clienteDTO.setEmail("Min.power@msn.com.co");
		clienteDTO.setNombre("Min Power");
		clienteDTO.setTdocId(1L);
		clienteDTO.setTelefono("12345678");
		restTemplate.put(url+"update", clienteDTO);
    }
	
	@Test
	@DisplayName("delete")
	void dTest() {
		RestTemplate restTemplate= new RestTemplate();
		restTemplate.delete(url+"delete/"+clieId);		
	}		

}
