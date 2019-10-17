package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;

@SpringBootTest
@Rollback(false)
class ClienteServiceTest {

	private final static Long clieId = 452620L;

	@Autowired
	ClienteService clienteService;

	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;

	@Test
	@DisplayName("save")
	void atest() {
		assertNotNull(clienteService);
		assertNotNull(tipoDocumentoRepository);

		Cliente cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida siempre viva 123");
		cliente.setEmail("homeroJSimpsson@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("555 555 5552");

		assertTrue(tipoDocumentoRepository.findById(1L).isPresent(), "El tipo documento no existe");

		cliente.setTipoDocumento(tipoDocumentoRepository.findById(1L).get());

		try {
			clienteService.save(cliente);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

	@Test
	@DisplayName("findById")
	void bTest() {
		assertNotNull(clienteService, "clienteService es nulo");
		Optional<Cliente> clienteOptional = clienteService.findById(clieId);
		assertTrue(clienteOptional.isPresent());
	}

	@Test
	@DisplayName("update")
	@Transactional
	void cTest() {
		assertNotNull(clienteService, "clienteService es nulo");
		Optional<Cliente> clienteOptional = clienteService.findById(clieId);
		assertTrue(clienteOptional.isPresent(), "el cliente no existe");

		Cliente cliente = clienteOptional.get();
		cliente.setActivo("N");

		try {
			clienteService.update(cliente);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}

	}

	@Test
	@DisplayName("delete")
	void dTest() {
		assertNotNull(clienteService, "clienteService es nulo");
		Optional<Cliente> clienteOptional = clienteService.findById(clieId);
		assertTrue(clienteOptional.isPresent(), "el cliente no existe");

		Cliente cliente = clienteOptional.get();
		try {
			clienteService.delete(cliente);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}

	}

	private final static Logger log = LoggerFactory.getLogger(ClienteServiceTest.class);

	@Test
	@DisplayName("findByNombre")
	void findByNombre() {
		assertNotNull(clienteService, "clienteService es nulo");
		List<Cliente> losClientes = clienteService.findByNombre("Jerrie Cannell");
		assertNotNull(losClientes);
		assertFalse(losClientes.isEmpty());
		for (Cliente cliente: losClientes) {
			log.info("Id: "+cliente.getClieId());
			log.info("Nombre: "+cliente.getNombre());
		}
	}
	
	@Test
	@DisplayName("findByNombreLike")
	void findByNombreLike() {
		assertNotNull(clienteService, "clienteService es nulo");
		List<Cliente> losClientes = clienteService.findByNombreLike("%ere%");
		assertNotNull(losClientes);
		assertFalse(losClientes.isEmpty());
		for (Cliente cliente: losClientes) {
			log.info("Id: "+cliente.getClieId());
			log.info("Nombre: "+cliente.getNombre());
		}
	}


}
