package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@Rollback(false)
class OperacionBancariaTest {

	private final static Logger log = LoggerFactory.getLogger(OperacionBancariaTest.class);
	
	@Autowired
	OperacionBancaria operacionBancaria;
	
	@Test
	@DisplayName("retirar")
	void retirar() {
		try {
			String cuenId= "4640-0341-9387-5781";
			BigDecimal valor= new BigDecimal(150000);
			String usuUsuario="callbrook0";
			Long numeroTransaccion=operacionBancaria.retirar(cuenId, valor, usuUsuario);
			log.info("Id:"+numeroTransaccion);
		} catch (Exception e) {
			log.error(e.getMessage());
			assertNull(e,e.getMessage());
			// TODO: handle exception
		}
	}
	
	@Test
	@DisplayName("consignar")
	void consignar() {
		try {
			String cuenId= "4640-0341-9387-5781";
			BigDecimal valor= new BigDecimal(150000);
			String usuUsuario="callbrook0";
			Long numeroTransaccion=operacionBancaria.consignar(cuenId, valor, usuUsuario);
			log.info("Id:"+numeroTransaccion);
		} catch (Exception e) {
			log.error(e.getMessage());
			assertNull(e,e.getMessage());
			// TODO: handle exception
		}
	}
	
	@Test
	@DisplayName("transferir")
	void transferir() {
		try {
			String cuenIdOrigen= "4640-0341-9387-5781";
			String cuenIdDestino= "6592-7866-3024-5314";
			BigDecimal valor= new BigDecimal(15000);
			String usuUsuario="callbrook0";
			Long numeroTransaccion=operacionBancaria.transferir(cuenIdOrigen,cuenIdDestino, valor, usuUsuario);
			log.info("Id:"+numeroTransaccion);
		} catch (Exception e) {
			log.error(e.getMessage());
			assertNull(e,e.getMessage());
			// TODO: handle exception
		}
	}

}
