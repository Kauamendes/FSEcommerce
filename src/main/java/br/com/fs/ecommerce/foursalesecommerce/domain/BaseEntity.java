package br.com.fs.ecommerce.foursalesecommerce.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class BaseEntity {

    @CreatedBy
    @Column(name = "criado_por", updatable = false)
    protected String criadoPor;

    @LastModifiedBy
    @Column(name = "alterado_por")
    protected String alteradoPor;

    @CreatedDate
    @Column(name = "criado_em", updatable = false)
    protected LocalDateTime criadoEm;

    @LastModifiedDate
    @Column(name = "alterado_em")
    protected LocalDateTime alteradoEm;
}