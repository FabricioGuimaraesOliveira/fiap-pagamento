package com.fiap.greentracefood.usecases.pagamento;


import java.math.BigDecimal;

import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;
import com.fiap.greentracefood.domain.entity.pagamento.gateway.PagamentoGateway;
import com.fiap.greentracefood.domain.entity.pagamento.model.Pagamento;
import com.fiap.greentracefood.infrastructure.mercadopago.gateway.MercadoPagoGateway;
import com.fiap.greentracefood.infrastructure.messaging.OrderSender;

public class PagamentoUseCase {

    private final PagamentoGateway pagamentoGateway;
    private final MercadoPagoGateway mercadoPagoGateway;
    private final OrderSender orderSender;

    public PagamentoUseCase(PagamentoGateway pagamentoGateway, MercadoPagoGateway mercadoPagoGateway,OrderSender orderSender) {
        this.pagamentoGateway = pagamentoGateway;
        this.mercadoPagoGateway = mercadoPagoGateway;
        this.orderSender = orderSender;
    }

    public Pagamento consultarPorPedido(String codigoPedido) {
        return pagamentoGateway.consultarPorPedido(codigoPedido).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    public String iniciarPagamento(String codigoPedido, BigDecimal value) {
    	String qrCode = gerarQRCode(codigoPedido,value);
        atualizarStatusPagamento(codigoPedido, qrCode);
        return qrCode;
    }

    private String gerarQRCode(String codigo, BigDecimal valor) {
        return mercadoPagoGateway.generateQRCodeMock(codigo, valor);
    }

    private void atualizarStatusPagamento(String codigoPedido,String qrCode) {
        Pagamento pagamento = new Pagamento();
        pagamento.setId(codigoPedido);
        pagamento.setStatus(StatusPagamento.PAGAMENTO_INICIADO);
        pagamento.setQrCode(qrCode);
        pagamentoGateway.salvar(pagamento);
    }
    public void registrarPagamento(String codigoPedido, StatusPagamento statusPagamento) {
        if (statusPagamento != StatusPagamento.REJEITADO && statusPagamento != StatusPagamento.APROVADO) {
            throw new IllegalArgumentException("O status do pagamento deve ser REJEITADO ou APROVADO");
        }
        Pagamento pagamento = pagamentoGateway.consultarPorPedido(codigoPedido).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
        pagamento.setStatus(statusPagamento);
        pagamentoGateway.salvar(pagamento);

        orderSender.sendProcessedPaymentMessage(pagamento);
    }
}
