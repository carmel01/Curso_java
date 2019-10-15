package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class OperacionBancariaTest {

	
	private final static Logger log = LoggerFactory.getLogger(OperacionBancariaTest.class);
	
	@Autowired
	IOperacionBancaria operacionBancaria;
	
	
	@Test
	@DisplayName("retirar")
	void retirar()  {
		try {
			assertNotNull(operacionBancaria,"Operacion bancaria es nulo");
		String cuenId ="4640-0341-9387-5781";
		BigDecimal valor = new BigDecimal(150000);
		String usuUsuario="callbrook0";
		Long numeroTransaccion=operacionBancaria.retirar(cuenId, valor, usuUsuario);
		assertNotNull(numeroTransaccion,"no retorno numero Transaccion");
		log.info("Id: "+numeroTransaccion);
		}catch (Exception e) {
			log.error(e.getMessage());
			assertNull(e,e.getMessage());
		}
	}
		
		@Test
		@DisplayName("consignar")
		void consignar()  {
			try {
				assertNotNull(operacionBancaria,"Operacion bancaria es nulo");
			String cuenId ="4640-0341-9387-5781";
			BigDecimal valor = new BigDecimal(50000);
			String usuUsuario="callbrook0";
			Long numeroTransaccion=operacionBancaria.consignar(cuenId, valor, usuUsuario);
			assertNotNull(numeroTransaccion,"no retorno numero Transaccion");
			log.info("Id: "+numeroTransaccion);
			}catch (Exception e) {
				log.error(e.getMessage());
				assertNull(e,e.getMessage());
			}
	
		
	}
		
		@Test
		@DisplayName("transferir")
		void transferir()  { 
			try {
				assertNotNull(operacionBancaria,"Operacion bancaria es nulo");
			String cuentaIdOrigen ="4640-0341-9387-5781";
			String cuentaIdDestino ="1630-2511-2937-7299";
			BigDecimal valor = new BigDecimal(15000);
			String usuUsuario="callbrook0";
			Long numeroTransaccion=operacionBancaria.transferir(cuentaIdOrigen, cuentaIdDestino, valor, usuUsuario);
			assertNotNull(numeroTransaccion,"no retorno numero Transaccion");
			log.info("Id: "+numeroTransaccion);
			}catch (Exception e) {
				log.error(e.getMessage());
				assertNull(e,e.getMessage());
			}
	
		
	}




}
