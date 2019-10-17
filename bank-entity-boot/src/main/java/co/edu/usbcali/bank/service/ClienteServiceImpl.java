package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.repository.ClienteRepository;
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

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
			throw new Exception("El cliente es nulo");
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
			throw new Exception("El cliente con id " + cliente.getClieId() + " ya existe");
		}
		if (tipoDocumentoRepository.findById(cliente.getTipoDocumento().getTdocId()).isPresent() == false) {
			throw new Exception("El tipo de documento con id " + cliente.getTipoDocumento().getTdocId() + " no existe");
		}

		return clienteRepository.save(cliente);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Cliente update(Cliente cliente) throws Exception {
		validar(cliente);

		if (clienteRepository.findById(cliente.getClieId()).isPresent() == false) {
			throw new Exception("El cliente con id " + cliente.getClieId() + " no existe");
		}
		
		if (tipoDocumentoRepository.findById(cliente.getTipoDocumento().getTdocId()).isPresent() == false) {
			throw new Exception("El tipo de documento con id " + cliente.getTipoDocumento().getTdocId() + " no existe");
		}
		/*
		Cliente entity=clienteRepository.findById(cliente.getClieId()).get();
		
		entity.setActivo(cliente.getActivo());
		entity.setDireccion(cliente.getDireccion());
		entity.setEmail(cliente.getEmail());
		entity.setFechaCreacion(cliente.getFechaCreacion());
		entity.setFechaModificacion(cliente.getFechaModificacion());
		entity.setNombre(cliente.getNombre());
		entity.setTelefono(cliente.getTelefono());
		entity.setTipoDocumento(cliente.getTipoDocumento());
		entity.setUsuCreador(cliente.getUsuCreador());
		entity.setUsuModificador(cliente.getUsuModificador());*/
		
		return clienteRepository.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Cliente cliente) throws Exception {
		validar(cliente);
		
		if (clienteRepository.findById(cliente.getClieId()).isPresent() == false) {
			throw new Exception("El cliente con id " + cliente.getClieId() + " no existe");
		}
		
		cliente = findById(cliente.getClieId()).get();
		
		if (cliente.getCuentaRegistradas().size()>0) {
			throw new Exception("No se puede borrar el cliente porque tiene cuentas registradas");
		}
		
		if (cliente.getCuentas().size()>0) {
			throw new Exception("No se puede borrar el cliente porque tiene cuentas");
		}
		
		clienteRepository.delete(cliente);
				
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {
		if (id==null || id<1) {
			throw new Exception("El id no puede ser 0 ni menor a 1");
		}
		
		if (findById(id).isPresent()==false) {
			throw new Exception("El cliente que desea eliminar no existe");
		}
		
		delete(findById(id).get());

	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findByNombre(String nombre) {		
		return clienteRepository.findByNombre(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findByNombreLike(String nombre) {
		return clienteRepository.findByNombreLike(nombre);
	}

}
