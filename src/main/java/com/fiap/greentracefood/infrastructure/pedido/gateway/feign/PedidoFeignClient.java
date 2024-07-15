package com.fiap.greentracefood.infrastructure.pedido.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fiap.greentracefood.infrastructure.pedido.dto.response.PedidoResponseDTO;

@FeignClient(name = "pedido-service", url = "${pedido.service.url}")
public interface PedidoFeignClient {

	@GetMapping("/{codigo}/detalhar")
	public ResponseEntity<PedidoResponseDTO> detalharPorCodigo(@PathVariable String codigo);
}