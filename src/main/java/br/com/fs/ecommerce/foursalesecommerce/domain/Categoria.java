package br.com.fs.ecommerce.foursalesecommerce.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

import static java.util.Objects.isNull;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria extends BaseEntity {

    @Id
    private String id;
    private String nome;
    private String descricao;

    @PrePersist
    public void prePersist() {
        if (isNull(id)) {
            id = UUID.randomUUID().toString();
        }
    }
}
