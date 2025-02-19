package br.com.fs.ecommerce.foursalesecommerce.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedBy
    @Column(name = "criado_por", updatable = false)
    protected String criadoPor;

    @CreatedDate
    @Column(name = "criado_em", updatable = false)
    protected OffsetDateTime criadoEm;

    @LastModifiedBy
    @Column(name = "alterado_por")
    protected String alteradoPor;

    @LastModifiedDate
    @Column(name = "alterado_em")
    protected OffsetDateTime alteradoEm;

    @Version
    protected Integer version;
}