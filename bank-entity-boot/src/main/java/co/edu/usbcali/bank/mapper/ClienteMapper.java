package co.edu.usbcali.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.dto.ClienteDTO;

@Mapper
public interface ClienteMapper {

	@Mapping(source="tipoDocumento.tdocId", target="tdocId")
	ClienteDTO clienteToClienteDTO(Cliente cliente);
	
	@Mapping(source="tdocId", target="tipoDocumento.tdocId")
	Cliente clienteDTOtoCliente(ClienteDTO clienteDTO);
	
	List<Cliente> toCliente(List<ClienteDTO> losClientesDTO);
	
	List<ClienteDTO> toClientesDTO (List<Cliente> losClientes);
	
}
