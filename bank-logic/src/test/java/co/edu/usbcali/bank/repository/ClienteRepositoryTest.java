package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(value = false)
class ClienteRepositoryTest {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;
	
	@Test
	@DisplayName("save")
	void atest() {
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
	}

}
