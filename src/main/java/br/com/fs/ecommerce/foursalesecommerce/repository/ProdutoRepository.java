package br.com.fs.ecommerce.foursalesecommerce.repository;

import br.com.fs.ecommerce.foursalesecommerce.domain.Produto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository<Produto, String> {

    @Modifying
    @Transactional
    @Query("UPDATE Produto p SET p.quantidade = p.quantidade - :quantidade WHERE p.id = :id")
    void updateQuantidadeById(@Param("id") String id, @Param("quantidade") Integer quantidade);

    @Modifying
    @Transactional
    @Query("UPDATE Produto p SET p.ativo=:ativo, p.excluidoEm=CURRENT_TIMESTAMP WHERE p.id =:id")
    void updateAtivoAndExcluidoEmById(@Param("id") String id,
                                      @Param("ativo") boolean ativo);
}
