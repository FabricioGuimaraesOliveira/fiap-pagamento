package com.fiap.greentracefood.infrastructure.persistence.pagamento;

import java.util.Objects;

import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class PagamentoEntity {

    private String id;
    private StatusPagamento status;
    private String qrCodeData;

    public PagamentoEntity() {}

    public PagamentoEntity(String id, StatusPagamento status, String qrCodeData) {
        this.id = id;
        this.status = status;
        this.qrCodeData = qrCodeData;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDbAttribute("status")
    public StatusPagamento getStatus() {
        return this.status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    @DynamoDbAttribute("qr_code")
    public String getQrCodeData() {
        return this.qrCodeData;
    }

    public void setQrCodeData(String qrCodeData) {
        this.qrCodeData = qrCodeData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PagamentoEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getStatus(), that.getStatus())
                && Objects.equals(getQrCodeData(), that.getQrCodeData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getQrCodeData());
    }

    @Override
    public String toString() {
        return "PagamentoEntity [id=" + id + ", status=" + status + ", qrCodeData=" + qrCodeData + "]";
    }
}
