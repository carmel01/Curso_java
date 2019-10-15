package co.edu.usbcali.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import co.edu.usbcali.bank.domain.TipoTransaccion;

@Repository
@Scope("singleton")
public class TipoTransaccionRepositoryImpl implements TipoTransaccionRepository{

	@PersistenceContext
	EntityManager entityManager;

	
	@Override
	public TipoTransaccion save(TipoTransaccion entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Optional<TipoTransaccion> findById(Long id) {
		return Optional.ofNullable(entityManager.find(TipoTransaccion.class, id));
	}

	@Override
	public List<TipoTransaccion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(TipoTransaccion entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
