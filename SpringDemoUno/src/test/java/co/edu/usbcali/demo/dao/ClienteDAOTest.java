package co.edu.usbcali.demo.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContextDAO.xml")
class ClienteDAOTest {

	@Autowired
	private ClienteDAO clienteDAO;

	@Test
	void test() {
		assertNotNull(clienteDAO, "El cliente DAO es Nulo");
	}
}
