package br.com.fs.ecommerce.foursalesecommerce.repository;

import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.ativo=:ativo, u.excluidoEm=CURRENT_TIMESTAMP WHERE u.id =:id")
    void updateAtivoAndExcluidoEmById(@Param("id") Long id,
                                      @Param("ativo") boolean ativo);
}
