package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

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
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class ClienteServiceTest {
	
	private final static Long clieId=450L;	

	@Autowired
	ClientService clienteService;

	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;

	@Test
	@DisplayName("save")
	void aTest() {
		assertNotNull(clienteService);
		assertNotNull(tipoDocumentoRepository);

		Cliente cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("prueba@gmail.com");
		cliente.setNombre("Max Power");
		cliente.setTelefono("233253455");
		
		assertTrue(tipoDocumentoRepository.findById(1L).isPresent(),"El tipo de documento no existe");
		cliente.setTipoDocumento(tipoDocumentoRepository.findById(1L).get());
		
		try {
			clienteService.save(cliente);
		} catch (Exception e) {
			assertNull(e,e.getMessage());
		}
		
	}
	
	@Test
	@DisplayName("findById")
	void bTest() {
		assertNotNull(clienteService,"El cliente es nulo");
		Optional<Cliente> clienteOptional=clienteService.findById(clieId);
		assertTrue(clienteOptional.isPresent());		
	}	
	
	@Test
	@DisplayName("update")
	void cTest()
	{
		assertNotNull(clienteService,"El repositorio es nulo");
		Optional<Cliente> clienOptional=clienteService.findById(clieId);
		assertTrue(clienOptional.isPresent(),"El cliente no existe");
		Cliente cliente=clienOptional.get();
		cliente.setActivo("Y");
		
		try {
			clienteService.update(cliente);
		} catch (Exception e) {
			assertNull(e,e.getMessage());
		}		
    }
	
	@Test
	@DisplayName("Delete")
	void dtest()
	{
		assertNotNull(clienteService,"El repositorio es nulo");
		Optional<Cliente> clienOptional=clienteService.findById(clieId);
		assertTrue(clienOptional.isPresent());
		Cliente cliente=clienOptional.get();
		try {
			clienteService.delete(cliente);	
		} catch (Exception e) {
			assertNull(e,e.getMessage());
		}
			
	}	
		

}
