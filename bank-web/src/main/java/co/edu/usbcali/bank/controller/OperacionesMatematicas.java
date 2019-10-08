package co.edu.usbcali.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operaciones")
public class OperacionesMatematicas {

	@GetMapping("/sumar/{n1}/{n2}")
	public Resultado sumar(@PathVariable("n1") Integer numUno, @PathVariable("n2") Integer numDos) {
		return new Resultado(numUno+numDos);
	}
	
	@GetMapping("/restar/{n1}/{n2}")
	public Resultado restar(@PathVariable("n1") Integer numUno, @PathVariable("n2") Integer numDos) {
		return new Resultado(numUno-numDos);
	}
	
	@GetMapping("/dividir/{n1}/{n2}")
	public Resultado dividir(@PathVariable("n1") Integer numUno, @PathVariable("n2") Integer numDos) {
		return new Resultado(numUno/numDos);
	}
	
	@GetMapping("/multiplicar/{n1}/{n2}")
	public Resultado multiplicar(@PathVariable("n1") Integer numUno, @PathVariable("n2") Integer numDos) {
		return new Resultado(numUno*numDos);
	}
}

class Resultado {
	private Integer valor;

	public Resultado(Integer valor) {
		super();
		this.valor = valor;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

}
