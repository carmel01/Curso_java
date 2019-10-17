package co.edu.usbcali.bank.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cuenta;
import co.edu.usbcali.bank.domain.TipoTransaccion;
import co.edu.usbcali.bank.domain.Transaccion;
import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.repository.CuentaRepository;
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;
import co.edu.usbcali.bank.repository.TipoTransaccionRepository;
import co.edu.usbcali.bank.repository.TransaccionRepository;
import co.edu.usbcali.bank.repository.UsuarioRepository;

@Service
@Scope("singleton")
public class OperacionBancariaServiceImpl implements OperacionBancariaService {

	private final static Logger log=LoggerFactory.getLogger(OperacionBancariaServiceImpl.class);
	
	@Autowired
	CuentaRepository cuentaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;//usar service
	
	@Autowired
	TipoTransaccionRepository tipoTransaccionRepository;
	
	@Autowired
	TransaccionRepository transaccionRepository;
	
	@PostConstruct
	void postConstruct() {
		log.info("######## Se ejecuto el postConstruct #####3");
	}
	
	@PreDestroy
	void preDestroy() {
		log.info("######## Se ejecuto el preDestroy #####3");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long retirar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {

		validar(cuenId, valor, usuUsuario);
		
		Cuenta cuenta=cuentaRepository.findById(cuenId).get();
		Usuario usuario=usuarioRepository.findById(usuUsuario).get();
		
		if (cuenta.getSaldo().compareTo(valor)==-1) {
			throw new Exception("No se puede realizar el retiro. Fondos insuficientes");
		}
		
		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()) );
		transaccion.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		transaccion.setTranId(null);
		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(1L).get();
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setValor(valor);
		
		cuenta.setSaldo(cuenta.getSaldo().subtract(valor));
		cuentaRepository.save(cuenta);
		
		transaccion=transaccionRepository.save(transaccion);
		return transaccion.getTranId();
	
	}

	private void validar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		if (cuenId == null || cuenId.trim().equals("") == true) {
			throw new Exception("El numero de la cuenta es obligatorio");
		}

		if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new Exception("El valor debe ser positivo");
		}

		if (usuUsuario == null || usuUsuario.trim().equals("") == true) {
			throw new Exception("El usuario es obligatorio");
		}

		Optional<Cuenta> cuentaOptional = cuentaRepository.findById(cuenId);

		if (cuentaOptional.isPresent() == false) {
			throw new Exception("La cuenta con id " + cuenId + " no existe");
		}
		if (cuentaOptional.get().getActiva().equals("N") == true) {
			throw new Exception("La cuenta con id " + cuenId + " se encuentra inactiva");
		}

		Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuUsuario);
		if (usuarioOptional.isPresent() == false) {
			throw new Exception("El usuario " + usuUsuario + " se encuentra inactivo");
		}
		if (usuarioOptional.get().getActivo().equals("N") == true) {
			throw new Exception("El usuario " + cuenId + " se encuentra inactivo");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long consignar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		validar(cuenId, valor, usuUsuario);
		
		Cuenta cuenta=cuentaRepository.findById(cuenId).get();
		Usuario usuario=usuarioRepository.findById(usuUsuario).get();
		
		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()) );
		transaccion.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		transaccion.setTranId(null);
		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(2L).get();
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setValor(valor);
		
		cuenta.setSaldo(cuenta.getSaldo().add(valor));
		cuentaRepository.save(cuenta);
		
		transaccion=transaccionRepository.save(transaccion);
		return transaccion.getTranId();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long transferir(String cuenIdOrigen, String cuenIdDestino, BigDecimal valor, String usuUsuario)
			throws Exception {
		retirar(cuenIdOrigen, valor, usuUsuario);
		consignar(cuenIdDestino, valor, usuUsuario);
		retirar(cuenIdOrigen, new BigDecimal(2000), usuUsuario);
		consignar("9999-9999-9999-9999", new BigDecimal(2000), usuUsuario);
		
		Cuenta cuenta=cuentaRepository.findById(cuenIdOrigen).get();
		Usuario usuario=usuarioRepository.findById(usuUsuario).get();
		
		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()) );
		transaccion.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		transaccion.setTranId(null);
		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(3L).get();
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setValor(valor);
		
		transaccion=transaccionRepository.save(transaccion);
		return transaccion.getTranId();
	}

}
