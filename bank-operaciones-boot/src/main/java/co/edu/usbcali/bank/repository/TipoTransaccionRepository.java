package co.edu.usbcali.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.TipoTransaccion;

public interface TipoTransaccionRepository extends JpaRepository<TipoTransaccion, Long> {

}
