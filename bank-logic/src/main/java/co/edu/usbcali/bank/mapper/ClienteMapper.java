package co.edu.usbcali.bank.mapper;

import org.mapstruct.Mapper;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.dto.ClienteDTO;

@Mapper
public interface ClienteMapper {

	ClienteDTO clienteToClienteDTO(Cliente cliente);
}
