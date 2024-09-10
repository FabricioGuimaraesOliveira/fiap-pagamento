package com.fiap.greentracefood.infrastructure.pedido.gateway;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.greentracefood.domain.entity.pedido.gateway.PedidoGateway;
import com.fiap.greentracefood.domain.entity.pedido.model.Pedido;
import com.fiap.greentracefood.infrastructure.pedido.gateway.feign.PedidoFeignClient;

@Service
public class PedidoGatewayImpl implements PedidoGateway {
	private final PedidoFeignClient pedidoFeignClient;
	private final ModelMapper modelMapper; 

	@Autowired
	public PedidoGatewayImpl(PedidoFeignClient pedidoFeignClient, ModelMapper modelMapper) {
		this.pedidoFeignClient = pedidoFeignClient;
		this.modelMapper = new ModelMapper();
	}

	@Override
	public Pedido detalharPorCodigo(String codigo) {
		return modelMapper.map(this.pedidoFeignClient.detalharPorCodigo(codigo).getBody(), Pedido.class);
	}
}