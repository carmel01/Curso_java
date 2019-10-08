package co.edu.usbcali.bank.dto;

public class ClienteDTO {

	private Long ClieId;
	private String activo;
	private String direccion;
	private String email;
	private String nombre;
	private String telefono;
	private Long tdocId;

	public Long getClieId() {
		return ClieId;
	}

	public void setClieId(Long clieId) {
		ClieId = clieId;
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

	public Long getTdocId() {
		return tdocId;
	}

	public void setTdocId(Long tdocId) {
		this.tdocId = tdocId;
	}
}
