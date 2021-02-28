package br.com.emanuelgabriel.clienteapi.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(length = 80, nullable = false)
    private String nome;

    @Column(length = 12, nullable = false)
    private String rg;

    public Cliente(){
    }

    public Cliente(Long id, String nome, String rg) {
        this.id = id;
        this.nome = nome;
        this.rg = rg;
    }

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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}
