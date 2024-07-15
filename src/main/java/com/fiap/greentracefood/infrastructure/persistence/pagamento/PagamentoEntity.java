package com.fiap.greentracefood.infrastructure.persistence.pagamento;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "pagamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEntity {
    @Id
    private String id;
    @Field("status")
    private StatusPagamento status;
    @Field("qr_code")
    private String qrCodeData;

}