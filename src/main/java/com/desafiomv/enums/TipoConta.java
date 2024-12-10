package com.desafiomv.enums;

public enum TipoConta {

    CORRENTE(1, "Conta Corrente"),
    POUPANCA(1, "Conta Poupança");


    private final int codigo;

    private final String descricao;

    TipoConta(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoConta valueOf(int codigo) {
        for (TipoConta tipoConta : values()) {
            if (tipoConta.codigo == codigo) {
                return tipoConta;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }

}
