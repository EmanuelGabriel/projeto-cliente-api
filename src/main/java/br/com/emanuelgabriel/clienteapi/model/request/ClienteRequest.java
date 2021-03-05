package br.com.emanuelgabriel.clienteapi.model.request;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ClienteRequest {

    @ApiModelProperty(value = "Nome do cliente", required = true)
    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    @ApiModelProperty(value = "CPF do cliente", required = true)
    @CPF(message = "CPF inválido")
    private String cpf;

    @ApiModelProperty(value = "Data de Nascimento do cliente", required = true)
    @NotNull(message = "Campo data de nascimento é obrigatório")
    private LocalDate dataNascimento;

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
