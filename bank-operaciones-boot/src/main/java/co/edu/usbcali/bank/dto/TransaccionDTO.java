package co.edu.usbcali.bank.dto;

import java.math.BigDecimal;

public class TransaccionDTO {
	private String cuenId;
	private BigDecimal valor;
	private String usuUsuario;

	public String getCuenId() {
		return cuenId;
	}

	public void setCuenId(String cuenId) {
		this.cuenId = cuenId;
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
