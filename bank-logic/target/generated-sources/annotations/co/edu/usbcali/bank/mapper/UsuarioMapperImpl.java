package co.edu.usbcali.bank.mapper;

import co.edu.usbcali.bank.domain.TipoUsuario;
import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.3.0.Final, compiler: Eclipse JDT (IDE) 3.17.0.v20190306-2240, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDTO UsuarioToUsuarioDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setTiusId( usuarioTipoUsuarioTiusId( usuario ) );
        usuarioDTO.setUsuUsuario( usuario.getUsuUsuario() );
        usuarioDTO.setActivo( usuario.getActivo() );
        usuarioDTO.setClave( usuario.getClave() );
        usuarioDTO.setFechaCreacion( usuario.getFechaCreacion() );
        usuarioDTO.setFechaModificacion( usuario.getFechaModificacion() );
        usuarioDTO.setIdentificacion( usuario.getIdentificacion() );
        usuarioDTO.setNombre( usuario.getNombre() );
        usuarioDTO.setUsuCreador( usuario.getUsuCreador() );
        usuarioDTO.setUsuModificador( usuario.getUsuModificador() );

        return usuarioDTO;
    }

    @Override
    public Usuario UsuarioDTOToUsuario(UsuarioDTO usuario) {
        if ( usuario == null ) {
            return null;
        }

        Usuario usuario1 = new Usuario();

        usuario1.setTipoUsuario( usuarioDTOToTipoUsuario( usuario ) );
        usuario1.setUsuUsuario( usuario.getUsuUsuario() );
        usuario1.setActivo( usuario.getActivo() );
        usuario1.setClave( usuario.getClave() );
        usuario1.setFechaCreacion( usuario.getFechaCreacion() );
        usuario1.setFechaModificacion( usuario.getFechaModificacion() );
        usuario1.setIdentificacion( usuario.getIdentificacion() );
        usuario1.setNombre( usuario.getNombre() );
        usuario1.setUsuCreador( usuario.getUsuCreador() );
        usuario1.setUsuModificador( usuario.getUsuModificador() );

        return usuario1;
    }

    @Override
    public List<Usuario> toUsuarios(List<UsuarioDTO> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<Usuario> list = new ArrayList<Usuario>( usuarios.size() );
        for ( UsuarioDTO usuarioDTO : usuarios ) {
            list.add( UsuarioDTOToUsuario( usuarioDTO ) );
        }

        return list;
    }

    @Override
    public List<UsuarioDTO> toUsuariosDTO(List<Usuario> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<UsuarioDTO> list = new ArrayList<UsuarioDTO>( usuarios.size() );
        for ( Usuario usuario : usuarios ) {
            list.add( UsuarioToUsuarioDTO( usuario ) );
        }

        return list;
    }

    private Long usuarioTipoUsuarioTiusId(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }
        TipoUsuario tipoUsuario = usuario.getTipoUsuario();
        if ( tipoUsuario == null ) {
            return null;
        }
        Long tiusId = tipoUsuario.getTiusId();
        if ( tiusId == null ) {
            return null;
        }
        return tiusId;
    }

    protected TipoUsuario usuarioDTOToTipoUsuario(UsuarioDTO usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        TipoUsuario tipoUsuario = new TipoUsuario();

        tipoUsuario.setTiusId( usuarioDTO.getTiusId() );

        return tipoUsuario;
    }
}
