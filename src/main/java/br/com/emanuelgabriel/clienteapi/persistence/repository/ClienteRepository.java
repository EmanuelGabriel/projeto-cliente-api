package br.com.emanuelgabriel.clienteapi.persistence.repository;

import br.com.emanuelgabriel.clienteapi.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
