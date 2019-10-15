package co.edu.usbcali.demo.vehiculo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class AutomovilAppUnoTest {

	@Test
	void test() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/applicationContextUno.xml");

		assertNotNull(applicationContext, "El aplicationContext es nulo");
		
		Automovil automovil = (Automovil)applicationContext.getBean("automovil");
		
		assertNotNull(automovil);
		
		assertNotNull(automovil.getMotor());
		
		assertFalse(automovil.getMotor().getElectrico());
	}

}
