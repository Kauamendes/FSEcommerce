package br.com.fs.ecommerce.foursalesecommerce.repository;

import br.com.fs.ecommerce.foursalesecommerce.domain.Produto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Produto p SET p.ativo=:ativo WHERE p.id =:id")
    void updateAtivoById(@Param("id") Long id,
                         @Param("ativo") boolean ativo);

    @Query("SELECT p FROM Produto p WHERE p.id IN :ids")
    List<Produto> findAllByIdComTenant(@Param("ids") List<Long> ids);

    Optional<Produto> findOneById(Long id);

    boolean existsOneById(Long id);
}
