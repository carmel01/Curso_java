package co.edu.usbcali.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class OperacionesMatematicas {

	@GetMapping("/sumar/{n1}/{n2}")
	public Integer sumar(@PathVariable("n1") Integer numUno, @PathVariable("n2") Integer numDos) {
		return numUno + numDos;
	}
}

class Resultado{
	private Integer valor;

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
}
