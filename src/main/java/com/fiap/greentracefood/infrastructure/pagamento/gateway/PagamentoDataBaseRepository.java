package com.fiap.greentracefood.infrastructure.pagamento.gateway;
import java.util.Optional;

import com.fiap.greentracefood.infrastructure.persistence.pagamento.PagamentoEntity;
import org.modelmapper.ModelMapper;
//import org.springframework.transaction.annotation.Transactional;

import com.fiap.greentracefood.domain.entity.pagamento.gateway.PagamentoGateway;
import com.fiap.greentracefood.domain.entity.pagamento.model.Pagamento;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Optional;
import java.util.UUID;

public class PagamentoDataBaseRepository implements PagamentoGateway {
    private final DynamoDbEnhancedClient enhancedClient;
    private final DynamoDbTable<PagamentoEntity> table;

    public PagamentoDataBaseRepository(DynamoDbEnhancedClient enhancedClient, String tableName) {
        this.enhancedClient = enhancedClient;
        this.table = enhancedClient.table(tableName, TableSchema.fromBean(PagamentoEntity.class));
    }

    @Override
    public void salvar(Pagamento pagamento) {
        PagamentoEntity pagamentoEntity = new PagamentoEntity(
                pagamento.getId(),  // Gera um UUID para o ID do pagamento
                pagamento.getStatus(),
                pagamento.getQrCode()
        );
        table.putItem(pagamentoEntity);
    }

    @Override
    public Optional<Pagamento> consultarPorPedido(String codigoPedido) {
        Key key = Key.builder()
                .partitionValue(codigoPedido)
                .build();

        PagamentoEntity pagamentoEntity = table.getItem(r -> r.key(key));

        if (pagamentoEntity != null) {
            Pagamento pagamento = new Pagamento(
                    pagamentoEntity.getId(),
                    pagamentoEntity.getStatus(),
                    pagamentoEntity.getQrCodeData()
            );
            return Optional.of(pagamento);
        }

        return Optional.empty();
    }
}
