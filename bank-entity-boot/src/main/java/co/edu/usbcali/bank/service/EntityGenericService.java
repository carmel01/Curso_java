package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;

public interface EntityGenericService<T,ID> {
	T save(T entity) throws Exception;
	T update(T entity) throws Exception;
	Optional<T> findById(ID id) ;
	List<T> findAll();
	void delete(T entity) throws Exception;
	void deleteById(ID id) throws Exception;
}

