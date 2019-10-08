package co.edu.usbcali.bank.mapper;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.dto.ClienteDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.3.0.Final, compiler: Eclipse JDT (IDE) 3.17.0.v20190306-2240, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDTO clienteToClienteDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setTdocId( clienteTipoDocumentoTdocId( cliente ) );
        clienteDTO.setClieId( cliente.getClieId() );
        clienteDTO.setActivo( cliente.getActivo() );
        clienteDTO.setDireccion( cliente.getDireccion() );
        clienteDTO.setEmail( cliente.getEmail() );
        clienteDTO.setNombre( cliente.getNombre() );
        clienteDTO.setTelefono( cliente.getTelefono() );

        return clienteDTO;
    }

    private Long clienteTipoDocumentoTdocId(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }
        TipoDocumento tipoDocumento = cliente.getTipoDocumento();
        if ( tipoDocumento == null ) {
            return null;
        }
        Long tdocId = tipoDocumento.getTdocId();
        if ( tdocId == null ) {
            return null;
        }
        return tdocId;
    }
}
