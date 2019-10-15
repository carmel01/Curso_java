package co.edu.usbcali.bank.service;

import java.math.BigDecimal;

public interface IOperacionBancaria {

	
	public Long retirar(String cuentaId, BigDecimal valor, String usuUsuario) throws Exception;

	public Long consignar(String cuentaId, BigDecimal valor, String usuUsuario) throws Exception;
	
	public Long transferir(String cuentaIdOrigen, String cuentaIdDestino, BigDecimal valor, String usuUsuario) throws Exception;
}
