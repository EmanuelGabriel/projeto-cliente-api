package br.com.emanuelgabriel.clienteapi.service.mapper;

import br.com.emanuelgabriel.clienteapi.model.response.ClienteResponse;
import br.com.emanuelgabriel.clienteapi.persistence.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteResponseMapper implements Mapper<Cliente, ClienteResponse> {

    @Override
    public ClienteResponse map(Cliente input) {

        if(input == null){
            return null; // pode ser tratado com uma exceção
        }

        ClienteResponse response = new ClienteResponse();
        response.setId(input.getId());
        response.setNome(input.getNome());
        response.setCpf(input.getCpf());
        response.setDataNascimento(input.getDataNascimento());

        return response;
    }
}
