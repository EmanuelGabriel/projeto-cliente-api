package br.com.emanuelgabriel.clienteapi.resource.impl;

import br.com.emanuelgabriel.clienteapi.model.request.ClienteRequest;
import br.com.emanuelgabriel.clienteapi.model.response.ClienteResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ClienteResourceService {

    @ApiOperation(value = "Cadastra um cliente", notes = "Este recurso adiciona um cliente", response = ClienteResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente cadastrado"),
            @ApiResponse(code = 400, message = "Bad request - erro do cliente"),
            @ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
            @ApiResponse(code = 403, message = "Forbidden - Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
    })
    ResponseEntity<ClienteResponse> criar(@Valid @RequestBody ClienteRequest request);

    @ApiOperation(value = "Lista de clientes", notes = "Este recurso lista todos os clientes cadastrados", response = ClienteResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request - erro do cliente"),
            @ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
            @ApiResponse(code = 403, message = "Forbidden - Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Not found - não encontrado"),
            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
    })
    ResponseEntity<Page<ClienteResponse>> getClientes(Pageable pageable);


    @ApiOperation(value = "Atualiza um cliente", notes = "Este recurso atualiza um cliente por ID", response = ClienteResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
            @ApiResponse(code = 403, message = "Forbidden - Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
    })
    ResponseEntity<ClienteResponse> atualizar(@ApiParam(name = "idCliente", value = "ID do cliente", required = true, example = "1") @PathVariable("idCliente") Long idCliente, @RequestBody ClienteRequest request);


    @ApiOperation(value = "Busca cliente por ID", notes = "Este recurso buscar um cliente por ID", response = ClienteResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
            @ApiResponse(code = 403, message = "Forbidden - Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
    })
    ResponseEntity<ClienteResponse> buscarPorID(@ApiParam(name = "idCliente", value = "ID do cliente", required = true, example = "1") @PathVariable("idCliente") Long idCliente);


    @ApiOperation(value = "Remove cliente por ID", notes = "Este recurso remove um cliente por ID", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content - sem conteúdo"),
            @ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
            @ApiResponse(code = 403, message = "Forbidden - Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
    })
    ResponseEntity<Void> remover(@ApiParam(name = "idCliente", value = "ID do cliente", required = true, example = "1") @PathVariable("idCliente") Long idCliente);


}
