package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.repository.TipoUsuarioRepository;
import co.edu.usbcali.bank.repository.UsuarioRepository;

@Service
@Scope("singleton")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;

	@Autowired
	Validator validator;

	public void validar(Usuario usuario) throws Exception {

		if (usuario == null) {
			throw new Exception("El usuario es nulo");
		}

		Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);

		if (constraintViolations.size() > 0) {
			StringBuilder strMessage = new StringBuilder();

			for (ConstraintViolation<Usuario> constraintViolation : constraintViolations) {
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
	public Usuario save(Usuario usuario) throws Exception {
		validar(usuario);

		if (usuarioRepository.findById(usuario.getUsuUsuario()).isPresent() == true) {
			throw new Exception("El usuario con id " + usuario.getUsuUsuario() + " ya existe");
		}
		if (tipoUsuarioRepository.findById(usuario.getTipoUsuario().getTiusId()).isPresent() == false) {
			throw new Exception("El tipo de usuario con id " + usuario.getTipoUsuario().getTiusId() + " no existe");
		}

		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Usuario update(Usuario usuario) throws Exception {
		validar(usuario);

		if (usuarioRepository.findById(usuario.getUsuUsuario()).isPresent() == false) {
			throw new Exception("El usuario con id " + usuario.getUsuUsuario() + " no existe");
		}
		
		if (tipoUsuarioRepository.findById(usuario.getTipoUsuario().getTiusId()).isPresent() == false) {
			throw new Exception("El tipo de usuario con id " + usuario.getTipoUsuario().getTiusId() + " no existe");
		}
		
		Usuario entity=usuarioRepository.findById(usuario.getUsuUsuario()).get();
		
		entity.setActivo(usuario.getActivo());
		entity.setClave(usuario.getClave());
		entity.setFechaCreacion(usuario.getFechaCreacion());
		entity.setFechaModificacion(usuario.getFechaModificacion());
		entity.setIdentificacion(usuario.getIdentificacion());
		entity.setNombre(usuario.getNombre());
		entity.setTipoUsuario(usuario.getTipoUsuario());
		entity.setTransaccions(usuario.getTransaccions());
		entity.setUsuCreador(usuario.getUsuCreador());
		entity.setUsuModificador(usuario.getUsuModificador());
		entity.setUsuUsuario(usuario.getUsuUsuario());
		
		return usuarioRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(String id) {
		return usuarioRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Usuario usuario) throws Exception {
		validar(usuario);
		
		if (usuarioRepository.findById(usuario.getUsuUsuario()).isPresent() == false) {
			throw new Exception("El usuario con id " + usuario.getUsuUsuario() + " no existe");
		}
		
		usuario = findById(usuario.getUsuUsuario()).get();
		
		if (usuario.getTransaccions().size()>0) {
			throw new Exception("No se puede borrar el cliente porque tiene transacciones registradas");
		}
		
		usuarioRepository.delete(usuario);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		if (id==null) {
			throw new Exception("El id no puede ser nulo");
		}
		
		if (findById(id).isPresent()==false) {
			throw new Exception("El usuario que desea eliminar no existe");
		}
		
		delete(findById(id).get());

	}

}
