package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.domain.Cliente;

public interface ClienteDAO {

	public Cliente save(Cliente cliente);
	public Cliente update(Cliente cliente);
	public void delete(Cliente cliente);
	public Cliente findById(Integer id);
	public List<Cliente> findAll();
}
