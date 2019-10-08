package co.edu.usbcali.bank.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.dto.ClienteDTO;

@Mapper
public interface ClienteMapper {

	@Mapping(source = "tipoDocumento.tdocId",target = "tdocId")
	ClienteDTO clienteToClienteDTO(Cliente cliente);
}
