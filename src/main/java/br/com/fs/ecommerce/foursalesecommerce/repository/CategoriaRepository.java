package br.com.fs.ecommerce.foursalesecommerce.repository;

import br.com.fs.ecommerce.foursalesecommerce.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriaRepository extends JpaRepository<Categoria, String> {

    @Modifying
    @Query("UPDATE categoria c SET c.ativo=:ativo, c.excluidoEm=CURRENT_TIMESTAMP WHERE c.id =:id")
    void updateAtivoAndExcluidoEmById(@Param("id") String id,
                                      @Param("ativo") boolean ativo);
}
