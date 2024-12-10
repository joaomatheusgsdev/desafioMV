package com.desafiomv.entidades;

import com.desafiomv.enums.TipoCliente;
import com.desafiomv.utils.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;


@Entity
@Table(name = "cliente_pj")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "cliente_id")
@Builder
public class ClientePJ extends Cliente {

  public ClientePJ(Long id, String nome, String email, TipoCliente tipoCliente, Boolean habilitado, Endereco endereco, String cnpj, String razaoSocial) {
    super(id, nome, email, tipoCliente, habilitado, endereco);
    this.cnpj = cnpj;
    this.razaoSocial = razaoSocial;
  }

  public ClientePJ() {
  }

  @Column
  private String cnpj;
  @Column
  private String razaoSocial;


  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getRazaoSocial() {
    return razaoSocial;
  }

  public void setRazaoSocial(String razaoSocial) {
    this.razaoSocial = razaoSocial;
  }
}