package co.edu.usbcali.bank.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.dto.UsuarioDTO;
import co.edu.usbcali.bank.mapper.UsuarioMapper;
import co.edu.usbcali.bank.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final static Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	UsuarioMapper usuarioMapper;
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		try {
			usuarioService.deleteById(id);
			return ResponseEntity.ok().body("");
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().body(new ResponseError(400,e.getMessage()));
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody UsuarioDTO usuarioDTO) {
		try {
			Usuario usuario = usuarioMapper.usuarioDTOtoUsuario(usuarioDTO);
			usuario = usuarioService.update(usuario);
			return ResponseEntity.ok().body("");
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().body(new ResponseError(400,e.getMessage()));
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody UsuarioDTO usuarioDTO) {
		try {
			Usuario usuario = usuarioMapper.usuarioDTOtoUsuario(usuarioDTO);
			usuario = usuarioService.save(usuario);
			return ResponseEntity.ok().body("");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError(400,e.getMessage()));
		}
	}
	
	@GetMapping("/findById/{id}")
	public UsuarioDTO findById(@PathVariable("id") String id) {
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		if (usuarioOptional.isPresent() == false) {
			return null;
		}
		Usuario usuario = usuarioOptional.get();
		return usuarioMapper.usuarioToUsuarioDTO(usuario);
	}
	
	@GetMapping("/findAll")
	public List<UsuarioDTO> findAll() {
		List<Usuario> listaUsuarios = usuarioService.findAll();
		List<UsuarioDTO> listaUsuariosDTO = usuarioMapper.toUsuarioDTO(listaUsuarios);
		return listaUsuariosDTO;
	}
}
