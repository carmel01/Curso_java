package co.edu.usbcali.bank.service;

import java.util.List;

import co.edu.usbcali.bank.domain.Cliente;

public interface ClienteService extends EntityGenericService<Cliente, Long>{
	public List<Cliente> findByNombre(String nombre);
	public List<Cliente> findByNombreLike(String nombre);
}
