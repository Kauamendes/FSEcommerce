package br.com.fs.ecommerce.foursalesecommerce.domain;

import br.com.fs.ecommerce.foursalesecommerce.annotations.Tsid;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tenants")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tenant extends BaseEntity {

    @Id
    @Tsid
    private Long id;
    private String nome;
    private String slug;
    private Boolean ativo;
}
