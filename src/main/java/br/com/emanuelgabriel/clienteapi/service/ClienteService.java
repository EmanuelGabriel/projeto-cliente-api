package br.com.emanuelgabriel.clienteapi.service;


import br.com.emanuelgabriel.clienteapi.model.request.ClienteRequest;
import br.com.emanuelgabriel.clienteapi.model.response.ClienteResponse;

public interface ClienteService {

    ClienteResponse criar(ClienteRequest request);

}
