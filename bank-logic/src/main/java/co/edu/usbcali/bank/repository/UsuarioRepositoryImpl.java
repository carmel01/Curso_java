package co.edu.usbcali.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.bank.domain.Usuario;
@Repository
@Scope("singleton")
public class UsuarioRepositoryImpl implements UsuarioRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Usuario save(Usuario entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Optional<Usuario> findById(String id) {
		Usuario usuario = entityManager.find(Usuario.class, id);
		Optional<Usuario> optional = Optional.ofNullable(usuario);
		return optional;
	}

	@Override
	public List<Usuario> findAll() {
		return entityManager.createQuery("FROM Usuario", Usuario.class).getResultList();
	}

	@Override
	public void delete(Usuario entity) {
		entityManager.remove(entity);

	}

	@Override
	public void deleteById(String id) {
		findById(id).ifPresent(t -> delete(t));
	}

}
