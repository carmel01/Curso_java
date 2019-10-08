package co.edu.usbcali.bank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.dto.ClienteDTO;
import co.edu.usbcali.bank.mapper.ClienteMapper;
import co.edu.usbcali.bank.repository.ClienteService;


@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@Autowired 
	ClienteMapper clienteMapper;
	
	@GetMapping("/findById/{id}")
	public ClienteDTO findById(@PathVariable("id") Long id) {
		Optional<Cliente> clienteOptional=clienteService.findById(id);
		if(clienteOptional.isPresent()==false)
		{
		return null;
		}
		Cliente cliente=clienteOptional.get();
		return clienteMapper.clienteToClienteDTO(cliente);
	}
}
