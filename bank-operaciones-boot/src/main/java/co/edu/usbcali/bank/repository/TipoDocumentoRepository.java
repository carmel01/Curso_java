package co.edu.usbcali.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.TipoDocumento;
//al implemetar la interfaz generica se evita crear los 5 metodos
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long>{

}
