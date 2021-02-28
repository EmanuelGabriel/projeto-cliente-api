package br.com.emanuelgabriel.clienteapi.service;

import br.com.emanuelgabriel.clienteapi.model.request.ClienteRequest;
import br.com.emanuelgabriel.clienteapi.model.response.ClienteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteService.class);

    @Override
    public ClienteResponse criar(ClienteRequest request) {
        return null;
    }

    @Override
    public Page<ClienteResponse> getClientes(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ClienteResponse> atualizar(Long id, ClienteRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<ClienteResponse> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean remover(Long id) {
        return false;
    }


}
