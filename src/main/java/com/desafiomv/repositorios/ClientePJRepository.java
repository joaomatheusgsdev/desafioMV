package com.desafiomv.repositorios;

import com.desafiomv.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientePJRepository extends JpaRepository<ClientePJ, Long> {

    <Optional> ClientePJ findClientePJByCnpj(String cnpj);
}