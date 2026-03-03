package com.finance.spring_app.DTO;

import java.math.BigDecimal;

public class SaldoDTO {

    private BigDecimal valor;

    public SaldoDTO() {
    }

    public SaldoDTO(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}