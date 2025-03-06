package br.com.fs.ecommerce.foursalesecommerce.repository;

import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByEmail(String email);

    @Modifying
    @Query("UPDATE usuario u SET u.ativo=:ativo, u.excluidoEm=CURRENT_TIMESTAMP WHERE u.id =:id")
    void updateAtivoAndExcluidoEmById(@Param("id") String id,
                                      @Param("ativo") boolean ativo);
}
