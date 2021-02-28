package br.com.emanuelgabriel.clienteapi.service;

import br.com.emanuelgabriel.clienteapi.model.request.ClienteRequest;
import br.com.emanuelgabriel.clienteapi.model.response.ClienteResponse;
import br.com.emanuelgabriel.clienteapi.persistence.entity.Cliente;
import br.com.emanuelgabriel.clienteapi.persistence.repository.ClienteRepository;
import br.com.emanuelgabriel.clienteapi.service.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private Mapper<ClienteRequest, Cliente> clienteRequestMapper;

    @Autowired
    private Mapper<Cliente, ClienteResponse> clienteResponseMapper;


    @Override
    public ClienteResponse criar(ClienteRequest request) {
        LOGGER.info("Criando um resgitro de cliente");
        Assert.notNull(request, "Request inválida");
        Cliente cliente = this.clienteRequestMapper.map(request);
        return clienteRepository.saveAndFlush(cliente).map((Cliente input) -> this.clienteResponseMapper.map(input));
    }

    @Override
    public Page<ClienteResponse> getClientes(Pageable pageable) {
        LOGGER.info("Buscar todos os clientes");
        Assert.notNull(pageable, "Página inválida");
        return this.clienteRepository.findAll(pageable).map(cliente -> this.clienteResponseMapper.map(cliente));
    }

    @Override
    public Optional<ClienteResponse> atualizar(Long id, ClienteRequest request) {
        LOGGER.info("Atualizando o registro do cliente");
        Assert.notNull(id, "ID inválido");

        Cliente clienteUpdate = this.clienteRequestMapper.map(request);
        return this.clienteRepository.findById(id).map(cli -> {
            cli.setNome(clienteUpdate.getNome());
            cli.setDataNascimento(clienteUpdate.getDataNascimento());
            return this.clienteResponseMapper.map(this.clienteRepository.saveAndFlush(cli));
        });

    }

    @Override
    public Optional<ClienteResponse> buscarPorId(Long id) {
        LOGGER.info("Buscar registro do cliente por ID");
        Assert.notNull(id, "ID inválido");
        return this.clienteRepository.findById(id).map(this.clienteResponseMapper::map);
    }

    @Override
    public boolean remover(Long id) {
        LOGGER.info("Remove registro do cliente por ID");
        Assert.notNull(id, "ID inválido");
        try {

            this.clienteRepository.deleteById(id);
            return true;
        }catch (Exception e){
            LOGGER.warn("Erro ao remover o registro do cliente de código {}", id);
        }

        return false;
    }

}
