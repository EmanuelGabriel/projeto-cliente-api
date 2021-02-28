package br.com.emanuelgabriel.clienteapi.service;


import br.com.emanuelgabriel.clienteapi.model.request.ClienteRequest;
import br.com.emanuelgabriel.clienteapi.model.response.ClienteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClienteService {

    ClienteResponse criar(ClienteRequest request);

    Page<ClienteResponse> getClientes(Pageable pageable);

    Optional<ClienteResponse> atualizar(Long id, ClienteRequest request);

    Optional<ClienteResponse> buscarPorId(Long id);

    boolean remover(Long id);

}
