package co.edu.usbcali.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.dto.UsuarioDTO;

@Mapper
public interface UsuarioMapper {
	
	@Mapping(source="tipoUsuario.tiusId", target="tiusId")
	UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);
	
	@Mapping(source="tiusId", target="tipoUsuario.tiusId")
	Usuario usuarioDTOtoUsuario(UsuarioDTO usuarioDTO);
	
	List<Usuario> toUsuario(List<UsuarioDTO> losUsuariosDTO);
	
	List<UsuarioDTO> toUsuarioDTO (List<Usuario> losUsuarios);
}
