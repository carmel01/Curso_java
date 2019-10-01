package co.edu.usbcali.demo.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.domain.Cliente;

@Repository
@Scope("singleton")
public class ClienteDAOImpl implements ClienteDAO{

	@Override
	public Cliente save(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente update(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
