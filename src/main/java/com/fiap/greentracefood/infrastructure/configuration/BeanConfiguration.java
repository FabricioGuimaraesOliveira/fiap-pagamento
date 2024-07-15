package com.fiap.greentracefood.infrastructure.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fiap.greentracefood.domain.entity.pagamento.gateway.PagamentoGateway;
import com.fiap.greentracefood.domain.entity.pedido.gateway.PedidoGateway;
import com.fiap.greentracefood.infrastructure.mercadopago.gateway.MercadoPagoGateway;
import com.fiap.greentracefood.infrastructure.pagamento.gateway.PagamentoDataBaseRepository;
import com.fiap.greentracefood.infrastructure.persistence.pagamento.SpringPagamentoRepository;
import com.fiap.greentracefood.usecases.pagamento.PagamentoUseCase;

@Configuration
public class BeanConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	PagamentoGateway createPagamentoGateway(SpringPagamentoRepository springPagamentoRepository, ModelMapper mapper) {
		return new PagamentoDataBaseRepository(springPagamentoRepository, mapper);
	}

	@Bean
	PagamentoUseCase createPagamentoUseCase(PagamentoGateway pagamentoGateway, PedidoGateway pedidoGateway,
			MercadoPagoGateway mercadoPagoGateway) {
		return new PagamentoUseCase(pagamentoGateway, pedidoGateway, mercadoPagoGateway);
	}
}
