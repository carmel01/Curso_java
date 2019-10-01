package co.edu.usbcali.demo.vehiculo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContextCinco.xml")
class AutomovilAppCincoTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private VehiculoService vehiculoService;

	@Test
	void test() {

		assertNotNull(applicationContext, "El aplicationContext es nulo");
		assertNotNull(vehiculoService, "El vehiculoService es nulo");

	}
}
