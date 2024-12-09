package com.desafiomv.enums;

public enum TipoDeTransacao {

    DEBITO(1, "DEBITO"), CREDITO(2, "CRÉDITO");

    private int cod;
    private String descricao;

    private TipoDeTransacao(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoDeTransacao toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (TipoDeTransacao x : TipoDeTransacao.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
