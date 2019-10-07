package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class ClienteServiceTest {

	private final static Long clieId = 4560L;

	@Autowired
	ClienteService clienteService;
	@Autowired
	TipoDocumentoRepository documentoRepository;

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
		cliente.setNombre("Fiayiño");
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

}
