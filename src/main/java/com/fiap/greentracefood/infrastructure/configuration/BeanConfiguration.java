package com.fiap.greentracefood.infrastructure.configuration;

import com.fiap.greentracefood.infrastructure.messaging.OrderSender;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fiap.greentracefood.domain.entity.pagamento.gateway.PagamentoGateway;
import com.fiap.greentracefood.infrastructure.mercadopago.gateway.MercadoPagoGateway;
import com.fiap.greentracefood.infrastructure.pagamento.gateway.PagamentoDataBaseRepository;
import com.fiap.greentracefood.usecases.pagamento.PagamentoUseCase;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Configuration
public class BeanConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	PagamentoGateway createPagamentoGateway(DynamoDbEnhancedClient enhancedClient, @Value("${dynamodb.tablename}") String tableName) {
			return new PagamentoDataBaseRepository(enhancedClient,tableName);
	}

	@Bean
	PagamentoUseCase createPagamentoUseCase(PagamentoGateway pagamentoGateway,
											MercadoPagoGateway mercadoPagoGateway, OrderSender orderSender) {
		return new PagamentoUseCase(pagamentoGateway, mercadoPagoGateway,orderSender);
	}
}
