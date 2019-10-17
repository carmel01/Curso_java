package co.edu.usbcali.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long>{

}
