package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.repository.TipoUsuarioRepository;
import co.edu.usbcali.bank.repository.UsuarioRepository;
import net.bytebuddy.asm.Advice.Enter;

public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;

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

		if (tipoUsuarioRepository.findById(usuario.getTipoUsuario().getTiusId()).isPresent() == false) {
			throw new Exception("el tipo de usuario con Id: " + usuario.getTipoUsuario().getTiusId() + " No existe");
		}
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Usuario update(Usuario usuario) throws Exception {

		validar(usuario);

		if (usuarioRepository.findById(usuario.getUsuUsuario()).isPresent() == false) {
			throw new Exception("el usuario con Id: " + usuario.getUsuUsuario() + " no existe");
		}

		if (tipoUsuarioRepository.findById(usuario.getTipoUsuario().getTiusId()).isPresent() == false) {
			throw new Exception("el tipo de usuario con Id: " + usuario.getTipoUsuario().getTiusId() + " No existe");
		}

		Usuario entity = usuarioRepository.findById(usuario.getUsuUsuario()).get();

		entity.setUsuUsuario(usuario.getUsuUsuario());
		entity.setActivo(usuario.getActivo());
		entity.setClave(usuario.getClave());
		entity.setFechaCreacion(usuario.getFechaCreacion());
		entity.setFechaModificacion(usuario.getFechaModificacion());
		entity.setIdentificacion(usuario.getIdentificacion());
		entity.setNombre(usuario.getNombre());
		entity.setUsuCreador(usuario.getUsuCreador());
		entity.setUsuModificador(usuario.getUsuModificador());
		entity.setTipoUsuario(usuario.getTipoUsuario());

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

		if (usuario == null || usuario.getUsuUsuario() == null || usuario.getUsuUsuario().isEmpty()) {
			throw new Exception("No se recibio usuario");
		}

		if (usuarioRepository.findById(usuario.getUsuUsuario()).isPresent() == false) {
			throw new Exception("el usuario con Id: " + usuario.getUsuUsuario() + " no existe");
		}
		usuario = findById(usuario.getUsuUsuario()).get();

		if (usuario.getTransaccions().size() > 0) {
			throw new Exception("No se puede borrar el usuario porque tiene transacciones registradas");

		}

		usuarioRepository.delete(usuario);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		if (id == null || id.isEmpty()) {
			throw new Exception("El usuario no puede ser nulo o menor a uno");

		}

		if (!findById(id).isPresent()) {
			throw new Exception("El usuario que desea eliminar no existe");

		}
		delete(findById(id).get());

	}

}
