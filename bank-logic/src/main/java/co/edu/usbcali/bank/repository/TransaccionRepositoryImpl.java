package co.edu.usbcali.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.bank.domain.Transaccion;

@Repository
@Scope("singleton")
public class TransaccionRepositoryImpl implements TransaccionRepository {

	
	@PersistenceContext
	EntityManager entityManager;


	@Override
	public Transaccion save(Transaccion entity) {
		 entityManager.persist(entity);
		 return entity;
	}

	@Override
	public Optional<Transaccion> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaccion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Transaccion entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

}
