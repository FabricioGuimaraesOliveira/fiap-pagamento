package com.fiap.greentracefood.infrastructure.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.greentracefood.usecases.pagamento.PagamentoUseCase;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentListener {

    private static final Logger logger = LoggerFactory.getLogger(PaymentListener.class);
    private final PagamentoUseCase pagamentoUseCase;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SqsTemplate sqsTemplate;

    @SqsListener("payment-queue")
    public void receiveMessage(String message) {
        try {
            PaymentListenerDto paymentDto = objectMapper.readValue(message, PaymentListenerDto.class);
            logger.info("Received payment: {}", paymentDto);
            pagamentoUseCase.iniciarPagamento(paymentDto.getCodigo(), paymentDto.getValorTotal());
        } catch (Exception e) {
            logger.error("Error processing message", e);
        }

    }
}
