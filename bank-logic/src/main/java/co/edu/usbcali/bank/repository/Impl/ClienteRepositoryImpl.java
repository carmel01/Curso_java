package co.edu.usbcali.bank.repository.Impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.repository.ClienteRepository;

@Repository
@Scope(scopeName = "singleton")
public class ClienteRepositoryImpl implements ClienteRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Cliente save(Cliente entity) {

		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Optional<Cliente> findById(Long id) {
		Cliente cliente = entityManager.find(Cliente.class, id);
		Optional<Cliente> optional = Optional.ofNullable(cliente);
		return optional;
	}

	@Override
	public List<Cliente> findAll() {
		return entityManager.createQuery("FROM Cliente", Cliente.class).getResultList();
	}

	@Override
	public void delete(Cliente entity) {
		entityManager.remove(entity);
	}

	@Override
	public void deleteById(Long id) {
		
		/* Tradicional
		Optional<Cliente> clienteOptional= findById(id);
		if(clienteOptional.isPresent())
			delete(clienteOptional.get());
		*/
		
		// Corta
		findById(id).ifPresent(cliente->delete(cliente));
	}

}
