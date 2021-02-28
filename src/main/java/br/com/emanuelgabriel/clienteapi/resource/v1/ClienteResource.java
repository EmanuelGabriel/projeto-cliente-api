package br.com.emanuelgabriel.clienteapi.resource.v1;


import br.com.emanuelgabriel.clienteapi.model.request.ClienteRequest;
import br.com.emanuelgabriel.clienteapi.model.response.ClienteResponse;
import br.com.emanuelgabriel.clienteapi.service.ClienteService;
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

@RestController
@RequestMapping(value = "/api/v1/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteResource.class);

    @Autowired
    ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponse> criar(@Valid @RequestBody ClienteRequest request){
        LOGGER.info("Requisição recebida {}", request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCliente}").buildAndExpand(request.getNome()).toUri();
        return ResponseEntity.created(location).body(this.clienteService.criar(request));
    }

    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> getClientes(Pageable pageable){
        LOGGER.info("Resposta de todos os clientes");
        Page<ClienteResponse> clienteResponses = this.clienteService.getClientes(pageable);
        return ResponseEntity.ok(clienteResponses);
    }

    @PutMapping(value = "/{idCliente}")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable("idCliente") Long idCliente, @RequestBody ClienteRequest request){
        LOGGER.info("Requisição recebida para atualizar registro {}", request);
        Optional<ClienteResponse> clienteOptional = this.clienteService.atualizar(idCliente, request);
        if(!clienteOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteOptional.get());
    }

    @GetMapping(value = "/{idCliente}")
    public ResponseEntity<ClienteResponse> buscarPorID(@PathVariable("idCliente") Long idCliente){
        LOGGER.info("Requisição recebida para buscar registro por ID {}", idCliente);
        Optional<ClienteResponse> clienteResponse = this.clienteService.buscarPorId(idCliente);
        return clienteResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping(value = "/{idCliente}")
    public ResponseEntity<Void> remover(@PathVariable("idCliente") Long idCliente){
        LOGGER.info("Requisição recebida para remoção do registro por ID {}", idCliente);
        if(this.clienteService.remover(idCliente)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
