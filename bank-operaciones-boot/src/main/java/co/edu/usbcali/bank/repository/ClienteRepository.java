package co.edu.usbcali.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	public List<Cliente> findByNombre(String nombre);
	public List<Cliente> findByNombreLike(String nombre);
}
