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
import co.edu.usbcali.bank.repository.ICuentaRepository;
import co.edu.usbcali.bank.repository.TipoTransaccionRepository;
import co.edu.usbcali.bank.repository.TransaccionRepository;

@Service
@Scope("singleton")
public class OperacionBancariaImpl implements IOperacionBancaria {
	
	private final static Logger log = LoggerFactory.getLogger(OperacionBancariaImpl.class);

	@Autowired
	ICuentaRepository cuentaRepository;

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	TipoTransaccionRepository tipoTransaccionRepository;
	
	@Autowired
	TransaccionRepository transaccionRepository;
	
	@PostConstruct
	void postConstruct() {
		log.info("############### se ejecuto postConstruct");
	}
	@PreDestroy
	void preDestroy() {
		log.info("############### se ejecuto preDestroy");
	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long retirar(String cuentaId, BigDecimal valor, String usuUsuario) throws Exception {

		validar(cuentaId, valor, usuUsuario);
		Cuenta cuenta = cuentaRepository.findById(cuentaId).get();
		Usuario usuario = usuarioService.findById(usuUsuario).get();

		if (cuenta.getSaldo().compareTo(valor) == -1) {

			throw new Exception("No se puede realizar el retiro fondos insuficientes");
		}

		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
		transaccion.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		transaccion.setTranId(null);
		
		TipoTransaccion tipoTransaccion= tipoTransaccionRepository.findById(1L).get();
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setValor(valor);
		
		cuenta.setSaldo(cuenta.getSaldo().subtract(valor));

		cuentaRepository.save(cuenta);
		
		transaccion=transaccionRepository.save(transaccion);
		return transaccion.getTranId();

	}

	private void validar(String cuentaId, BigDecimal valor, String usuUsuario) throws Exception {
		if (cuentaId == null || cuentaId.trim().equals("") == true) {

			throw new Exception("El numero de la cuenta es obligatorio");

		}
		if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {

			throw new Exception("El valor debe ser positivo");

		}
		if (usuUsuario == null || usuUsuario.trim().equals("") == true) {

			throw new Exception("El id debe ser obligatorio");

		}
		Optional<Cuenta> cuentaOptional = cuentaRepository.findById(cuentaId);
		if (cuentaOptional.isPresent() == false) {
			throw new Exception("la cuenta con id: " + cuentaId + "no existe");

		}
		if (cuentaOptional.get().getActiva().equals("N") == true) {
			throw new Exception("la cuenta con id: " + cuentaId + " se encuentra in-activa");

		}

		Optional<Usuario> usuarioOptional = usuarioService.findById(usuUsuario);

		if (usuarioOptional.isPresent() == false) {
			throw new Exception("El usuario  con id: " + usuUsuario + "no existe");

		}

		if (usuarioOptional.get().getActivo().equals("N") == true) {
			throw new Exception("El usuario " + usuUsuario + " esta  in-activo");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long consignar(String cuentaId, BigDecimal valor, String usuUsuario) throws Exception {

		validar(cuentaId, valor, usuUsuario);
		Cuenta cuenta = cuentaRepository.findById(cuentaId).get();
		Usuario usuario = usuarioService.findById(usuUsuario).get();

		

		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
		transaccion.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		transaccion.setTranId(null);
		
		TipoTransaccion tipoTransaccion= tipoTransaccionRepository.findById(2L).get();
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setValor(valor);
		
		cuenta.setSaldo(cuenta.getSaldo().subtract(valor));

		cuentaRepository.save(cuenta);
		
		transaccion=transaccionRepository.save(transaccion);
		return transaccion.getTranId();

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long transferir(String cuentaIdOrigen, String cuentaIdDestino, BigDecimal valor, String usuUsuario)
			throws Exception {
		retirar(cuentaIdOrigen, valor, usuUsuario);
		consignar(cuentaIdDestino, valor, usuUsuario);
		retirar(cuentaIdOrigen,new BigDecimal(2000), usuUsuario);
		consignar("9999-9999-9999-9999", new BigDecimal(2000), usuUsuario);
		
		Cuenta cuenta = cuentaRepository.findById(cuentaIdOrigen).get();
		Usuario usuario = usuarioService.findById(usuUsuario).get();
		
		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
		transaccion.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		transaccion.setTranId(null);
		
		TipoTransaccion tipoTransaccion= tipoTransaccionRepository.findById(3L).get();
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setValor(valor);
		
		
		transaccion=transaccionRepository.save(transaccion);
		return transaccion.getTranId();
		

	}

}
