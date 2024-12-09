package com.desafiomv.entidades;

import com.desafiomv.enums.TipoCliente;
import com.desafiomv.utils.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;


@NoArgsConstructor
@Entity
@Table(name = "cliente_pj")
@Getter
@Setter
@Where(clause = "habilitado = true")
public class ClientePJ extends Cliente {

  public ClientePJ(Long id, String nome, String email, Enum tipoCliente, Boolean habilitado, String cnpj, String razaoSocial) {
    super(id, nome, email, tipoCliente, habilitado);
    super.tipoCliente = TipoCliente.PESSOAJURIDICA;
    this.cnpj = cnpj;
    this.razaoSocial = razaoSocial;
  }

  @Column
  private String cnpj;
  @Column
  private String razaoSocial;



}