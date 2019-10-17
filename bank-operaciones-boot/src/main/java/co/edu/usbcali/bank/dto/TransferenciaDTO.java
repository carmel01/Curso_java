package co.edu.usbcali.bank.dto;

import java.math.BigDecimal;

public class TransferenciaDTO {
	public String cuenIdOrigen;
	public String cuenIdDestino;
	public BigDecimal valor;
	public String usuUsuario;
	public String getCuenIdOrigen() {
		return cuenIdOrigen;
	}
	public void setCuenIdOrigen(String cuenIdOrigen) {
		this.cuenIdOrigen = cuenIdOrigen;
	}
	public String getCuenIdDestino() {
		return cuenIdDestino;
	}
	public void setCuenIdDestino(String cuenIdDestino) {
		this.cuenIdDestino = cuenIdDestino;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getUsuUsuario() {
		return usuUsuario;
	}
	public void setUsuUsuario(String usuUsuario) {
		this.usuUsuario = usuUsuario;
	}
	
}
