package co.edu.usbcali.bank.repository;

import java.util.List;
import java.util.Optional;

import co.edu.usbcali.bank.domain.Usuario;

public interface JPAGenericRepository<T,ID> {
	
	T save(T entity);	
	Optional<T> findById(ID id);
	List<T> findAll();
	void delete(T entity);
	void deleteById(ID id);
	

}
