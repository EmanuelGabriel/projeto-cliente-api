package br.com.emanuelgabriel.clienteapi.resource.v1;


import br.com.emanuelgabriel.clienteapi.model.request.ClienteRequest;
import br.com.emanuelgabriel.clienteapi.model.response.ClienteResponse;
import br.com.emanuelgabriel.clienteapi.service.ClienteService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@Api(produces = "application/json", consumes = "application/json", value = "Gerencia clientes", tags = {"Gerencia os clientes"})
@RestController
@RequestMapping(value = "/api/v1/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteResource.class);

    @Autowired
    ClienteService clienteService;

    @ApiOperation(value = "Cadastra um cliente", notes = "Este recurso adiciona um cliente", response = ClienteResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente cadastrado"),
            @ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
            @ApiResponse(code = 403, message = "Forbidden - Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponse> criar(@Valid @RequestBody ClienteRequest request){
        LOGGER.info("Requisição recebida {}", request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCliente}").buildAndExpand(request.getCpf()).toUri();
        return ResponseEntity.created(location).body(this.clienteService.criar(request));
    }

    @ApiOperation(value = "Lista de clientes", notes = "Este recurso lista todos os clientes cadastrados", response = ClienteResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
            @ApiResponse(code = 403, message = "Forbidden - Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
    })
    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> getClientes(Pageable pageable){
        LOGGER.info("Resposta de todos os clientes");
        Page<ClienteResponse> clienteResponses = this.clienteService.getClientes(pageable);
        return ResponseEntity.ok(clienteResponses);
    }

    @ApiOperation(value = "Atualiza um cliente", notes = "Este recurso atualiza um cliente por ID", response = ClienteResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
            @ApiResponse(code = 403, message = "Forbidden - Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
    })
    @PutMapping(value = "/{idCliente}")
    public ResponseEntity<ClienteResponse> atualizar(@ApiParam(name = "idCliente", value = "ID do cliente", required = true, example = "1") @PathVariable("idCliente") Long idCliente, @RequestBody ClienteRequest request){
        LOGGER.info("Requisição recebida para atualizar registro {}", request);
        Optional<ClienteResponse> clienteOptional = this.clienteService.atualizar(idCliente, request);
        if(!clienteOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteOptional.get());
    }


    @ApiOperation(value = "Busca cliente por ID", notes = "Este recurso buscar um cliente por ID", response = ClienteResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
            @ApiResponse(code = 403, message = "Forbidden - Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
    })
    @GetMapping(value = "/{idCliente}")
    public ResponseEntity<ClienteResponse> buscarPorID(@ApiParam(name = "idCliente", value = "ID do cliente", required = true, example = "1") @PathVariable("idCliente") Long idCliente){
        LOGGER.info("Requisição recebida para buscar registro por ID {}", idCliente);
        Optional<ClienteResponse> clienteResponse = this.clienteService.buscarPorId(idCliente);
        return clienteResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }


    @ApiOperation(value = "Remove cliente por ID", notes = "Este recurso remove um cliente por ID", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content - sem conteúdo"),
            @ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
            @ApiResponse(code = 403, message = "Forbidden - Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
    })
    @DeleteMapping(value = "/{idCliente}")
    public ResponseEntity<Void> remover(@ApiParam(name = "idCliente", value = "ID do cliente", required = true, example = "1") @PathVariable("idCliente") Long idCliente){
        LOGGER.info("Requisição recebida para remoção do registro por ID {}", idCliente);
        if(this.clienteService.remover(idCliente)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
