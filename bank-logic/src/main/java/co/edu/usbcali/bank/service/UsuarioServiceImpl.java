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
import co.edu.usbcali.bank.repository.ClienteRepository;
import co.edu.usbcali.bank.repository.TipoUsuarioRepository;
import co.edu.usbcali.bank.repository.UsuarioRepository;

@Service
@Scope("singleton")
public class UsuarioServiceImpl  implements UsuarioService{

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	TipoUsuarioRepository tipoUsuariorepository;
	
	@Autowired
	Validator validator;

	public void validar(Usuario usuario) throws Exception {
		if (usuario == null) {
			throw new Exception("el usuario es nulo");
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
			throw new Exception("el usuario con Id: " + usuario.getUsuUsuario() + " ya existe");
		}
		if (tipoUsuariorepository.findById(usuario.getTipoUsuario().getTiusId()).isPresent() == false) {
			throw new Exception("el tipo de usuario con Id: " + usuario.getTipoUsuario().getTiusId() + " No existe");
		}
		
		return usuarioRepository.save(usuario);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public Usuario update(Usuario usuario) throws Exception {
		validar(usuario);

		if (usuarioRepository.findById(usuario.getUsuUsuario()).isPresent() == false) {
			throw new Exception("el usuario con Id: " + usuario.getUsuUsuario() + " No existe");
		}
		if (tipoUsuariorepository.findById(usuario.getTipoUsuario().getTiusId()).isPresent() == false) {
			throw new Exception("el tipo de usuario con Id: " + usuario.getTipoUsuario().getTiusId() + " No existe");
		}
		
		Usuario entity = usuarioRepository.findById(usuario.getUsuUsuario()).get();
		entity.setActivo(usuario.getActivo());
		entity.setClave(usuario.getClave());
		entity.setFechaCreacion(usuario.getFechaCreacion());
		entity.setFechaModificacion(usuario.getFechaModificacion());
		entity.setIdentificacion(usuario.getIdentificacion());
		entity.setNombre(usuario.getNombre());
		entity.setTipoUsuario(usuario.getTipoUsuario());
		entity.setUsuCreador(usuario.getUsuCreador());
		entity.setUsuModificador(usuario.getUsuModificador());
		entity.setUsuUsuario(usuario.getUsuUsuario());
		
		
		
		

		return usuarioRepository.save(entity);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Usuario> findById(String usuUsuario) {
		return usuarioRepository.findById(usuUsuario);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void delete(Usuario usuario) throws Exception {
		validar(usuario);

		if (usuarioRepository.findById(usuario.getUsuUsuario()).isPresent() == false) {
			throw new Exception("el usuario con Id: " + usuario.getUsuUsuario() + " No existe");
		}
		usuario = findById(usuario.getUsuUsuario()).get();
		if (usuario.getTransaccions().size() > 0) {
			throw new Exception("No se puede borrar el cliente transacciones registradas");

		}
		

		usuarioRepository.delete(usuario);

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void deleteById(String usuUsuario) throws Exception {
		if (usuUsuario == null || usuUsuario.isEmpty()) {

		}

		if (!findById(usuUsuario).isPresent()) {
			throw new Exception("El usuario que desea eliminar no existe");

		}
		delete(findById(usuUsuario).get());

	}
}
