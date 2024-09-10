package com.fiap.greentracefood.domain.entity.pagamento.model;

import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;


public class Pagamento {
    private String id;

    public Pagamento(String id, StatusPagamento status, String qrCodeData) {
        this.id = id;
        this.status = status;
        this.qrCode = qrCodeData;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    private String qrCode;
    private StatusPagamento status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPedido() {
        return pedido;
    }

    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }

    private Long pedido;

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }


    public Pagamento() {
    }

    public static PagamentoBuilder builder() {
        return new PagamentoBuilder();
    }

    public static class PagamentoBuilder {
        private Pagamento instancia = new Pagamento();

        private PagamentoBuilder() {
        }

        public PagamentoBuilder id(String id) {
            instancia.id = id;
            return this;
        }

        public PagamentoBuilder pago(StatusPagamento pago) {
            instancia.status = pago;
            return this;
        }

        public Pagamento build() {
            return instancia;
        }
    }
}