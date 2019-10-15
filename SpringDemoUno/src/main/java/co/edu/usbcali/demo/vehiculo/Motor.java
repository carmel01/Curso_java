package co.edu.usbcali.demo.vehiculo;

public class Motor {

	private Integer cilindraje;
	private String marca;
	private Boolean electrico;

	public Integer getCilindraje() {
		return cilindraje;
	}

	public Motor() {

	}

	public Motor(Integer cilindraje, String marca, Boolean electrico) {
		super();
		this.cilindraje = cilindraje;
		this.marca = marca;
		this.electrico = electrico;
	}

	public void setCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Boolean getElectrico() {
		return electrico;
	}

	public void setElectrico(Boolean electrico) {
		this.electrico = electrico;
	}
}
