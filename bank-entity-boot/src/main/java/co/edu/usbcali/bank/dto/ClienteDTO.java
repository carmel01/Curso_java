package co.edu.usbcali.bank.dto;

import java.sql.Timestamp;

public class ClienteDTO {

	private Long clieId;

	private String activo;

	private String direccion;

	private String email;

	private Timestamp fechaCreacion;

	private Timestamp fechaModificacion;

	private String nombre;

	private String telefono;

	private String usuCreador;

	private String usuModificador;

	private Long tdocId;

	public Long getClieId() {
		return clieId;
	}

	public void setClieId(Long clieId) {
		this.clieId = clieId;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsuCreador() {
		return usuCreador;
	}

	public void setUsuCreador(String usuCreador) {
		this.usuCreador = usuCreador;
	}

	public String getUsuModificador() {
		return usuModificador;
	}

	public void setUsuModificador(String usuModificador) {
		this.usuModificador = usuModificador;
	}

	public Long getTdocId() {
		return tdocId;
	}

	public void setTdocId(Long tdocId) {
		this.tdocId = tdocId;
	}
}
