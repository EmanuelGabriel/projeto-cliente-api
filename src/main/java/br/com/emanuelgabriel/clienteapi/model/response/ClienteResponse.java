package br.com.emanuelgabriel.clienteapi.model.response;


import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

public class ClienteResponse {

    @ApiModelProperty(name = "ID do cliente")
    private Long id;

    @ApiModelProperty(value = "Nome do cliente")
    private String nome;

    @ApiModelProperty(value = "CPF do cliente")
    private String cpf;

    @ApiModelProperty(value = "Data de Nascimento do cliente")
    private LocalDate dataNascimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
