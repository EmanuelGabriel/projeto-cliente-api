package br.com.emanuelgabriel.clienteapi.service.exception;


public class ClienteNaoEncontradoException extends RuntimeException{

    public ClienteNaoEncontradoException(String message) {
        super(message);
    }
}
