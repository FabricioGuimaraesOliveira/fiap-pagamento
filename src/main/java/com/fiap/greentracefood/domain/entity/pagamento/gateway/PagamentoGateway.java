package com.fiap.greentracefood.domain.entity.pagamento.gateway;

import java.util.Optional;

import com.fiap.greentracefood.domain.entity.pagamento.model.Pagamento;

public interface PagamentoGateway {
   Optional<Pagamento> consultarPorPedido(String codigoPedido);
    void salvar(Pagamento pagamento);
}
