package co.edu.usbcali.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.domain.TipoUsuario;
@Repository
@Scope("singleton")
public class TipoUsuarioRepositoryImpl implements TipoUsuarioRepository{

	@PersistenceContext
	EntityManager entityManager;

	
	@Override
	public TipoUsuario save(TipoUsuario entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Optional<TipoUsuario> findById(Long id) {
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, id);
		Optional<TipoUsuario> optional = Optional.ofNullable(tipoUsuario);
		return optional;
	}

	@Override
	public List<TipoUsuario> findAll() {
		return entityManager.createQuery("FROM TipoUsuario", TipoUsuario.class).getResultList();
	}

	@Override
	public void delete(TipoUsuario entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public void deleteById(Long id) {
		findById(id).ifPresent(tipoUsuario -> delete(tipoUsuario));
		
	}

	
}
