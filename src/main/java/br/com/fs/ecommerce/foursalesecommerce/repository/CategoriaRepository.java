package br.com.fs.ecommerce.foursalesecommerce.repository;

import br.com.fs.ecommerce.foursalesecommerce.domain.Categoria;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Categoria c SET c.ativo=:ativo, c.excluidoEm=CURRENT_TIMESTAMP WHERE c.id =:id")
    void updateAtivoAndExcluidoEmById(@Param("id") Long id,
                                      @Param("ativo") boolean ativo);
}
