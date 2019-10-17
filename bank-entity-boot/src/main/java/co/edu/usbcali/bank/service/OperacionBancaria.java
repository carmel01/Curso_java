package co.edu.usbcali.bank.service;

import java.math.BigDecimal;

public interface OperacionBancaria {
	public Long retirar(String cuenId,BigDecimal valor,String usuUsuario)throws Exception;
	public Long consignar(String cuenId,BigDecimal valor,String usuUsuario)throws Exception;
	public Long transferir(String cuenIdOrigen,String cuenIdDestino,BigDecimal valor,String usuUsuario)throws Exception;
}
