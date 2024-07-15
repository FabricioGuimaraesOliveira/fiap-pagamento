package com.fiap.greentracefood.infrastructure.pagamento.gateway;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.greentracefood.domain.entity.pagamento.gateway.PagamentoGateway;
import com.fiap.greentracefood.domain.entity.pagamento.model.Pagamento;
import com.fiap.greentracefood.infrastructure.persistence.pagamento.PagamentoEntity;
import com.fiap.greentracefood.infrastructure.persistence.pagamento.SpringPagamentoRepository;


public class PagamentoDataBaseRepository implements PagamentoGateway {
    private  final SpringPagamentoRepository springPagamentoRepository;
    private final ModelMapper modelMapper;

    public PagamentoDataBaseRepository(SpringPagamentoRepository springPagamentoRepository, ModelMapper modelMapper) {
        this.springPagamentoRepository = springPagamentoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pagamento> consultarPorPedido(String codigoPedido) {
        //return springPagamentoRepository.findByPedidoCodigo(codigoPedido).map(pagamentoEntity -> modelMapper.map(pagamentoEntity, Pagamento.class));
    	
    	return null;
    }


    @Override
    public void salvar(Pagamento pagamento) {
    	var pagamentoEntity = modelMapper.map(pagamento, PagamentoEntity.class);
        springPagamentoRepository.save(pagamentoEntity);
    }
}
