package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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

import co.edu.usbcali.bank.domain.Cliente;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class ClienteRepositoryTest {

	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	TipoDocumentoRepository documentoRepository;

	private final static Long clieId = 4560L;
	private final static Logger log = LoggerFactory.getLogger(ClienteRepositoryTest.class);

	@Test
	@DisplayName("insert")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void aTest() {
		assertNotNull(clienteRepository);
		assertNotNull(documentoRepository);
		
		assertFalse(clienteRepository.findById(clieId).isPresent());

		Cliente cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setEmail("j@gmail.com");
		cliente.setDireccion("uni san buenaventura");
		cliente.setNombre("Fiayiño");
		cliente.setTelefono("321");
		assertTrue(documentoRepository.findById(1L).isPresent(), "el tipo de documento no existe");
		cliente.setTipoDocumento(documentoRepository.findById(1L).get());

		clienteRepository.save(cliente);

	}

	@Test
	@DisplayName("finById")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void bTest() {

		assertNotNull(clienteRepository);
		assertNotNull(documentoRepository);
		// Optional<Cliente> clienteOptional =clienteRepository.findById(clieId);
		assertTrue(clienteRepository.findById(clieId).isPresent(),"no existe");

	}

	@Test
	@DisplayName("update")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void cTest() {

		assertNotNull(clienteRepository);
		assertNotNull(documentoRepository);
		assertTrue(clienteRepository.findById(clieId).isPresent());

		Cliente cliente = clienteRepository.findById(clieId).get();
		cliente.setActivo("N");
		clienteRepository.save(cliente);

	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void dTest() {

		assertNotNull(clienteRepository);
		assertNotNull(documentoRepository);
		assertTrue(clienteRepository.findById(clieId).isPresent());
		Cliente cliente = clienteRepository.findById(clieId).get();
		clienteRepository.delete(cliente);

	}

	@Test
	@DisplayName("listAll")
	@Transactional(readOnly = true)
	void eTest() {

		assertNotNull(clienteRepository);
		assertNotNull(documentoRepository);

		List<Cliente> losClientes = clienteRepository.findAll();

		assertNotNull(losClientes);
		assertFalse(losClientes.isEmpty());

		for (Cliente cliente : losClientes) {
			log.info(cliente.getNombre());

		}

		losClientes.forEach(t -> {
			log.info(t.getClieId().toString());
		});

	}

}
