package co.edu.usbcali.demo.vehiculo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContextCuatro.xml")
class AutomovilAppCuatroTest {

	private final static Logger log = LoggerFactory.getLogger(AutomovilAppCuatroTest.class);

	@Autowired
	private ApplicationContext applicationContext;
	@Test
	void test() {		

		assertNotNull(applicationContext, "El aplicationContext es nulo");

		for (int i = 0; i < 10; i++) {
			Motor motor = (Motor) applicationContext.getBean("motor");
			log.info("Motor: " + motor);
		}
	}
}
