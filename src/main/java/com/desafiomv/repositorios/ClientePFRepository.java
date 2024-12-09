package com.desafiomv.repositorios;

import com.desafiomv.entidades.ClientePF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientePFRepository extends JpaRepository<ClientePF, Long> {
}