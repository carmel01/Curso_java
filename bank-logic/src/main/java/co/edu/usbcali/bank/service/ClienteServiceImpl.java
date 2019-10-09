package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.repository.ClienteRepository;
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;

@Service
@Scope("singleton")
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;

	@Autowired
	Validator validator;

	public void validar(Cliente cliente) throws Exception {
		if (cliente == null) {
			throw new Exception("el cliente es nulo");
		}
		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);

		if (constraintViolations.size() > 0) {
			StringBuilder strMessage = new StringBuilder();

			for (ConstraintViolation<Cliente> constraintViolation : constraintViolations) {
				strMessage.append(constraintViolation.getPropertyPath().toString());
				strMessage.append(" - ");
				strMessage.append(constraintViolation.getMessage());
				strMessage.append(". \n");
			}

			throw new Exception(strMessage.toString());
		}

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Cliente save(Cliente cliente) throws Exception {

		validar(cliente);

		if (clienteRepository.findById(cliente.getClieId()).isPresent() == true) {
			throw new Exception("el cliente con Id: " + cliente.getClieId() + " ya existe");
		}
		if (tipoDocumentoRepository.findById(cliente.getTipoDocumento().getTdocId()).isPresent() == false) {
			throw new Exception("el tipo documento con Id: " + cliente.getTipoDocumento().getTdocId() + " No existe");
		}
		return clienteRepository.save(cliente);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public Cliente update(Cliente cliente) throws Exception {
		validar(cliente);

		if (clienteRepository.findById(cliente.getClieId()).isPresent() == false) {
			throw new Exception("el cliente con Id: " + cliente.getClieId() + " no existe");
		}
		if (tipoDocumentoRepository.findById(cliente.getTipoDocumento().getTdocId()).isPresent() == false) {
			throw new Exception("el tipo documento con Id: " + cliente.getTipoDocumento().getTdocId() + " No existe");
		}
		Cliente entity = clienteRepository.findById(cliente.getClieId()).get();
		entity.setClieId(cliente.getClieId());
		entity.setActivo(cliente.getActivo());
		entity.setDireccion(cliente.getDireccion());
		entity.setEmail(cliente.getEmail());
		entity.setFechaCreacion(cliente.getFechaCreacion());
		entity.setFechaModificacion(cliente.getFechaModificacion());
		entity.setNombre(cliente.getNombre());
		entity.setTelefono(cliente.getTelefono());
		entity.setUsuCreador(cliente.getUsuCreador());
		entity.setUsuModificador(cliente.getUsuModificador());
		entity.setTipoDocumento(cliente.getTipoDocumento());

		return clienteRepository.save(entity);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void delete(Cliente cliente) throws Exception {
		validar(cliente);

		if (clienteRepository.findById(cliente.getClieId()).isPresent() == false) {
			throw new Exception("el cliente con Id: " + cliente.getClieId() + " no existe");
		}
		cliente = findById(cliente.getClieId()).get();
		if (cliente.getCuentaRegistradas().size() > 0) {
			throw new Exception("No se puede borrar el cliente porque tiene cuentas registradas");

		}
		if (cliente.getCuentas().size() > 0) {
			throw new Exception("No se puede borrar el cliente porque tiene cuentas ");

		}

		clienteRepository.delete(cliente);

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void deleteById(Long id) throws Exception {
		if (id == null || id < 1) {
			throw new Exception("El id no puede ser nulo o menor a uno");

		}

		if (!findById(id).isPresent()) {
			throw new Exception("El cliente que desea eliminar no existe");

		}
		delete(findById(id).get());

	}

}
