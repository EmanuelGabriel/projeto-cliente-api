package br.com.emanuelgabriel.clienteapi.service.exception;


public class ClienteCodigoNaoEncontradoException extends RuntimeException{

    public ClienteCodigoNaoEncontradoException(String message) {
        super(message);
    }
}
