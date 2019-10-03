package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import co.edu.usbcali.bank.domain.Cliente;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(value = false)
class ClienteRepositoryTest {

	private final static Long clieId = 4560L;

	private final static Logger log = LoggerFactory.getLogger(ClienteRepositoryTest.class);

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;

	@Test
	@DisplayName("save")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void atest() {
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
		assertFalse(clienteRepository.findById(clieId).isPresent(), "El cliente ya existe");

		Cliente cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida siempre viva 123");
		cliente.setEmail("HomerJSimpson@gmail.com");
		cliente.setNombre("Homer Jay Simpson");
		cliente.setTelefono("555 555 555");

		assertTrue(tipoDocumentoRepository.findById(1L).isPresent(), "El tipo de documento no existe");
		cliente.setTipoDocumento(tipoDocumentoRepository.findById(1L).get());

		clienteRepository.save(cliente);
	}

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void btest() {
		assertNotNull(clienteRepository, "El clienteRepository es nulo");
		Optional<Cliente> clienteOptional = clienteRepository.findById(clieId);
		assertTrue(clienteOptional.isPresent(), "El cliente con id " + clieId + " no existe");

	}

	@Test
	@DisplayName("update")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void ctest() {
		assertNotNull(clienteRepository, "El clienteRepository es nulo");

		Optional<Cliente> clienteOptional = clienteRepository.findById(clieId);
		assertTrue(clienteOptional.isPresent(), "El cliente con id " + clieId + " no existe");

		Cliente cliente = clienteOptional.get();
		cliente.setActivo("N");

		clienteRepository.save(cliente);
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void dtest() {
		assertNotNull(clienteRepository, "El clienteRepository es nulo");

		Optional<Cliente> clienteOptional = clienteRepository.findById(clieId);
		assertTrue(clienteOptional.isPresent(), "El cliente con id " + clieId + " no existe");

		Cliente cliente = clienteOptional.get();
		clienteRepository.delete(cliente);
	}

	@Test
	@DisplayName("deleteById")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void etest() {

		assertNotNull(clienteRepository, "El clienteRepository es nulo");

		// Se crea el cliente para poder borrarlo nuevamente
		atest();
		Optional<Cliente> clienteOptional = clienteRepository.findById(clieId);
		assertTrue(clienteOptional.isPresent(), "El cliente con id " + clieId + " no existe");

		clienteRepository.deleteById(clienteOptional.get().getClieId());
	}

	@Test
	@DisplayName("findAll")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void ftest() {
		assertNotNull(clienteRepository, "El clienteRepository es nulo");
		List<Cliente> clientes = clienteRepository.findAll();
		assertNotNull(clientes);

		assertFalse(clientes.isEmpty());

		clientes.forEach(clie -> {
			log.info(clie.getNombre());
		});
	}
}