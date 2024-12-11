package com.desafiomv.repositorios;

import com.desafiomv.entidades.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {


    Optional<Empresa> findEmpresaByCnpj(String cnpj);
    Optional<Empresa> findEmpresaByEmail(String email);
    Optional<Empresa> findEmpresaByNome(String nome);
}