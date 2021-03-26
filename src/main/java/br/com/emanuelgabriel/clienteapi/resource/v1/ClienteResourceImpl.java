package br.com.emanuelgabriel.clienteapi.resource.v1;


import br.com.emanuelgabriel.clienteapi.model.request.ClienteRequest;
import br.com.emanuelgabriel.clienteapi.model.response.ClienteResponse;
import br.com.emanuelgabriel.clienteapi.resource.impl.ClienteResourceService;
import br.com.emanuelgabriel.clienteapi.service.ClienteService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ClienteResourceImpl implements ClienteResourceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteResourceImpl.class);

    private final ClienteService clienteService;

    public ClienteResourceImpl(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponse> criar(@Valid @RequestBody ClienteRequest request){
        LOGGER.info("Requisição recebida {}", request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCliente}").buildAndExpand(request.getCpf()).toUri();
        return ResponseEntity.created(location).body(this.clienteService.criar(request));
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> getClientes(Pageable pageable){
        LOGGER.info("Resposta de todos os clientes");
        Page<ClienteResponse> clienteResponses = this.clienteService.getClientes(pageable);
        return ResponseEntity.ok(clienteResponses);
    }

    @Override
    @PutMapping(value = "/{idCliente}")
    public ResponseEntity<ClienteResponse> atualizar(@ApiParam(name = "idCliente", value = "ID do cliente", required = true, example = "1") @PathVariable("idCliente") Long idCliente, @RequestBody ClienteRequest request){
        LOGGER.info("Requisição recebida para atualizar registro {}", request);
        Optional<ClienteResponse> clienteOptional = this.clienteService.atualizar(idCliente, request);
        if(!clienteOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteOptional.get());
    }


    @Override
    @GetMapping(value = "/{idCliente}")
    public ResponseEntity<ClienteResponse> buscarPorID(@ApiParam(name = "idCliente", value = "ID do cliente", required = true, example = "1") @PathVariable("idCliente") Long idCliente){
        LOGGER.info("Requisição recebida para buscar registro por ID {}", idCliente);
        Optional<ClienteResponse> clienteResponse = this.clienteService.buscarPorId(idCliente);
        return clienteResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }


    @Override
    @DeleteMapping(value = "/{idCliente}")
    public ResponseEntity<Void> remover(@ApiParam(name = "idCliente", value = "ID do cliente", required = true, example = "1") @PathVariable("idCliente") Long idCliente){
        LOGGER.info("Requisição recebida para remoção do registro por ID {}", idCliente);
        if(this.clienteService.remover(idCliente)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
