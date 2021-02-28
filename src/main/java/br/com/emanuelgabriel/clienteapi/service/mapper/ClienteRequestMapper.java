package br.com.emanuelgabriel.clienteapi.service.mapper;

import br.com.emanuelgabriel.clienteapi.model.request.ClienteRequest;
import br.com.emanuelgabriel.clienteapi.persistence.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteRequestMapper implements Mapper<ClienteRequest, Cliente> {

    @Override
    public Cliente map(ClienteRequest input) {
        if(input == null){
            return null;
        }

        Cliente cliente = new Cliente();
        cliente.setNome(input.getNome());
        cliente.setCpf(input.getCpf());
        cliente.setDataNascimento(input.getDataNascimento());

        return cliente;
    }
}
