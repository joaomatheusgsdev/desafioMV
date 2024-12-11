package com.desafiomv.entidades;

import com.desafiomv.enums.TipoConta;
import com.desafiomv.enums.TipoDeTransacao;
import com.desafiomv.utils.Cliente;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String codigoBanco;

    private String codigoAgencia;

    private String numeroConta;

    private String digitoConta;

    private String digitoAgencia;

    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonManagedReference
    private Cliente cliente;


    @ManyToOne
    @JoinColumn(name = "empresa_id")
    @JsonManagedReference
    private Empresa empresa;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    Set<Movimentacao> movimentacoes = new HashSet<>();

    private BigDecimal saldo;

    ZonedDateTime dataAtualização;

    private boolean habilitada = true;

    public Conta(Long id, String codigoBanco, String codigoAgencia, String numeroConta, String digitoConta, String digitoAgencia, TipoConta tipoConta, Cliente cliente, Set<Movimentacao> movimentacoes, Empresa empresa, BigDecimal saldo) {
        this.id = id;
        this.codigoBanco = codigoBanco;
        this.codigoAgencia = codigoAgencia;
        this.numeroConta = numeroConta;
        this.digitoConta = digitoConta;
        this.digitoAgencia = digitoAgencia;
        this.tipoConta = tipoConta;
        this.cliente = cliente;
        this.movimentacoes = movimentacoes;
        this.empresa = null;
        this.saldo = BigDecimal.valueOf(0.0);
        this.habilitada = true;
        this.dataAtualização = null;
    }

    public Conta() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public String getCodigoAgencia() {
        return codigoAgencia;
    }

    public void setCodigoAgencia(String codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getDigitoConta() {
        return digitoConta;
    }

    public void setDigitoConta(String digitoConta) {
        this.digitoConta = digitoConta;
    }

    public String getDigitoAgencia() {
        return digitoAgencia;
    }

    public void setDigitoAgencia(String digitoAgencia) {
        this.digitoAgencia = digitoAgencia;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }


    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    public ZonedDateTime getDataAtualização() {
        return dataAtualização;
    }

    public void setDataAtualização(ZonedDateTime dataAtualização) {
        this.dataAtualização = dataAtualização;
    }

    public void addMovimentacao(Movimentacao movimentacao) {
        this.movimentacoes.add(movimentacao);
    }

    public void atualizacaoDeSaldo() {
        BigDecimal saldoAtualizado = BigDecimal.valueOf(0.0);

        for (Movimentacao movimentacao : this.movimentacoes) {
            if (movimentacao.getTipoDeTransacao().equals(TipoDeTransacao.CREDITO)) {
                saldoAtualizado = saldoAtualizado.add(movimentacao.getValor());
            } else {
                saldoAtualizado = saldoAtualizado.subtract(movimentacao.getValor());
            }
        }

        this.saldo = saldoAtualizado;

    }

    public int movimentacoesUltimosTrintaDias() {
        int contadorMovimentacoes = 0;
        for (Movimentacao movimentacao : this.movimentacoes) {
            if (movimentacao.getDataDeAtualizacao().isAfter(ZonedDateTime.now().minusDays(30))) {
                contadorMovimentacoes++;
            }
        }
        return contadorMovimentacoes;
    }

}