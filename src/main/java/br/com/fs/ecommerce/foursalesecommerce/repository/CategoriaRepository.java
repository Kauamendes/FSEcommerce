package br.com.fs.ecommerce.foursalesecommerce.repository;

import br.com.fs.ecommerce.foursalesecommerce.domain.Categoria;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Categoria c SET c.ativo=:ativo WHERE c.id =:id")
    void updateAtivoById(@Param("id") Long id,
                         @Param("ativo") boolean ativo);

    Optional<Categoria> findOneById(Long id);

    boolean existsOneById(Long id);
}
