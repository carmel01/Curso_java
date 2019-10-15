package co.edu.usbcali.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.bank.domain.Cuenta;


@Repository
@Scope("singleton")
public class CuentaRepositoryImpl implements ICuentaRepository {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Cuenta save(Cuenta entity) {
	entityManager.persist(entity);
	return entity; 
	}

	@Override
	public Optional<Cuenta> findById(String id) {
		Cuenta cuenta=entityManager.find(Cuenta.class, id);
		return Optional.ofNullable(cuenta);
	}

	@Override
	public List<Cuenta> findAll() {
		
		return entityManager.createQuery("From Cuenta",Cuenta.class).getResultList();
	}

	@Override
	public void delete(Cuenta entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public void deleteById(String id) {
		findById(id).ifPresent(entity->delete(entity));
		
	}

}
