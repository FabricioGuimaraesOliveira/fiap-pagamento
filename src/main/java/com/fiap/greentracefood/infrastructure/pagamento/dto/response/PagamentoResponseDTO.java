package com.fiap.greentracefood.infrastructure.pagamento.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagamentoResponseDTO {
    @Schema(description = "Codigo do Pagamento", example = "2345677")
    private String id;
    @Schema(description = "Status do pagamento", example = "PAGO|NAO_PAGO")
    private StatusPagamento statusPagamento;
    @Schema(description = "Dados do QR Code para pagamento")
    private String qrCodeData;

}
