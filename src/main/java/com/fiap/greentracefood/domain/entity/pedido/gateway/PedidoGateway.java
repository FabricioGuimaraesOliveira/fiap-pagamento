package com.fiap.greentracefood.domain.entity.pedido.gateway;

import com.fiap.greentracefood.domain.entity.pedido.model.Pedido;

public interface PedidoGateway {
	Pedido detalharPorCodigo(String codigo);
}