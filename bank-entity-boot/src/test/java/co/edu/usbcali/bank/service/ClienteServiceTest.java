package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;

@SpringBootTest
@Rollback(false)
class ClienteServiceTest {

	private final static Long clieId = 4560L;

	@Autowired
	ClienteService clienteService;

	@Autowired
	TipoDocumentoRepository documentoRepository;

	private final static Logger log=LoggerFactory.getLogger(ClienteServiceTest.class);
	@Test
	@DisplayName("save")
	void aTest() {
		
		assertNotNull(clienteService);
		assertNotNull(documentoRepository);

		Cliente cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setEmail("j@gmail.com");
		cliente.setDireccion("uni san buenaventura");
		cliente.setNombre("Fiayi�o");
		cliente.setTelefono("321255552");
		assertTrue(documentoRepository.findById(1L).isPresent(), "el tipo de documento no existe");
		cliente.setTipoDocumento(documentoRepository.findById(1L).get());
		
		try {
			clienteService.save(cliente);
			
		} catch (Exception e) {
			assertNull(e,e.getMessage());
		}

	}
	
	@Test
	@DisplayName("update")
	void cTest() {

	
		assertNotNull(clienteService,"El clienteService es nulo");
		assertTrue(clienteService.findById(clieId).isPresent());

		Cliente cliente = clienteService.findById(clieId).get();
		cliente.setActivo("N");
		
		try {
			clienteService.update(cliente);
			
		} catch (Exception e) {
			assertNull(e,e.getMessage());
		}

		
	}

	@Test
	@DisplayName("delete")
	void dTest() {

		assertNotNull(clienteService,"El clienteService es nulo");
		assertTrue(clienteService.findById(clieId).isPresent());

		Cliente cliente = clienteService.findById(clieId).get();
	
		
		try {
			clienteService.delete(cliente);
			
		} catch (Exception e) {
			assertNull(e,e.getMessage());
		}
	}
	
	@Test
	@DisplayName("findByNombre")
	void eTest() {

		assertNotNull(clienteService,"El clienteService es nulo");
		
		List<Cliente> clientes=clienteService.findByNombre("Karole Dunge");
		
		assertNotNull(clientes,"nulo en los clientes");
		
		assertFalse(clientes.isEmpty(),"No hay clientes para mostrar con ese nombre");
		
		for (Cliente cliente : clientes) {
			log.info("Id: "+cliente.getClieId());
			log.info("Nombre: "+cliente.getNombre());
		}
	}
	
	@Test
	@DisplayName("findByNombreLike")
	void fTest() {

		assertNotNull(clienteService,"El clienteService es nulo");
		
		List<Cliente> clientes=clienteService.findByNombreLike("Kar%");
		
		assertNotNull(clientes,"nulo en los clientes");
		
		assertFalse(clientes.isEmpty(),"No hay clientes para mostrar con ese nombre");
		
		for (Cliente cliente : clientes) {
			log.info("Id: "+cliente.getClieId());
			log.info("Nombre: "+cliente.getNombre());
		}
	}

}
