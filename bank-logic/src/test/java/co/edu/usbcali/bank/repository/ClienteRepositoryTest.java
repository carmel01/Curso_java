package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

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
@Rollback(false)
class ClienteRepositoryTest {
	private final static Long clieId=450L;
	
	private final static Logger log=LoggerFactory.getLogger(ClienteRepositoryTest.class);
	
	
    @Autowired
    ClienteRepository clienteRepository;
    
    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;
    
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void aTest() {
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
		assertFalse(clienteRepository.findById(clieId).isPresent(),"El cliente ya existe");
		   
	   Cliente cliente= new Cliente();
	   cliente.setActivo("S");
	   cliente.setClieId(clieId);
	   cliente.setDireccion("Avenida Siempre Viva 123");
	   cliente.setEmail("prueba@gmail.com");
	   cliente.setNombre("Max Power");
	   cliente.setTelefono("2332534");	
	   
	   assertTrue(tipoDocumentoRepository.findById(1L).isPresent(),"El tipo de documento no existe");
	   cliente.setTipoDocumento(tipoDocumentoRepository.findById(1L).get());
	   clienteRepository.save(cliente);
	   
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {
		assertNotNull(clienteRepository,"El cliente es nulo");
		Optional<Cliente> clienteOptional=clienteRepository.findById(clieId);
		assertTrue(clienteOptional.isPresent());		
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED )
	void cTest()
	{
		assertNotNull(clienteRepository,"El repositorio es nulo");
		Optional<Cliente> clienOptional=clienteRepository.findById(clieId);
		assertTrue(clienOptional.isPresent());
		Cliente cliente=clienOptional.get();
		cliente.setActivo("N");
		
		clienteRepository.save(cliente);
    }
	
	
	@Test
	@DisplayName("Delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED )
	void dtest()
	{
		assertNotNull(clienteRepository,"El repositorio es nulo");
		Optional<Cliente> clienOptional=clienteRepository.findById(clieId);
		assertTrue(clienOptional.isPresent());
		Cliente cliente=clienOptional.get();
		clienteRepository.delete(cliente);		
	}
	
	
	@Test
	@DisplayName("Find All")
	@Transactional(readOnly = true)
	void etest()
	{
		assertNotNull(clienteRepository,"El repositorio es nulo");
		List<Cliente> clientes=clienteRepository.findAll();
		assertNotNull(clientes);
		assertFalse(clientes.isEmpty());
		
		clientes.forEach(cliente->{log.info(cliente.getNombre());});
	}

}
