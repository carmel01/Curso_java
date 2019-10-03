package co.edu.usbcali.bank.repository.Impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;

@Repository
@Scope(scopeName = "singleton")
public class TipoDocumentoRepositoryImpl implements TipoDocumentoRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public TipoDocumento save(TipoDocumento entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Optional<TipoDocumento> findById(Long id) {
		return Optional.ofNullable(entityManager.find(TipoDocumento.class, id));
	}

	@Override
	public List<TipoDocumento> findAll() {
		return entityManager.createQuery("FROM TipoDocumento", TipoDocumento.class).getResultList();
	}

	@Override
	public void delete(TipoDocumento entity) {
		entityManager.remove(entity);
	}

	@Override
	public void deleteById(Long id) {
		findById(id).ifPresent(tipoDoc -> delete(tipoDoc));
	}

}
