package com.desafiomv.repositorios;

import com.desafiomv.entidades.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {

  Conta findByNumeroContaAndCodigoAgencia(String numeroConta, String codigoAgencia);

  Conta findContaByCodigoAgenciaAndNumeroConta(String agencia, String numeroConta);
}