package com.desafiomv.repositorios;

import com.desafiomv.entidades.Conta;
import com.desafiomv.utils.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findAllByHabilitadoTrue();

    <Optional> Cliente findByContas(Conta conta);
}