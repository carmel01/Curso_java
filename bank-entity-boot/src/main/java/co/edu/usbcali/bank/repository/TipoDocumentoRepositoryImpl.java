package co.edu.usbcali.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.bank.domain.TipoDocumento;

@Repository
@Scope("singleton")
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
		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, id);
		Optional<TipoDocumento> optional = Optional.ofNullable(tipoDocumento);
		return optional;
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
		findById(id).ifPresent(tipoDocumento -> delete(tipoDocumento));
	}

}
