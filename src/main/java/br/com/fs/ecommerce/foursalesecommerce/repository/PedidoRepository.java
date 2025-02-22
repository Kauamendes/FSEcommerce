package br.com.fs.ecommerce.foursalesecommerce.repository;

import br.com.fs.ecommerce.foursalesecommerce.domain.Pedido;
import br.com.fs.ecommerce.foursalesecommerce.domain.Status;
import br.com.fs.ecommerce.foursalesecommerce.dto.TicketMedioDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.TopCompradorDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, String> {

    @Modifying
    @Transactional
    @Query("UPDATE Pedido p SET p.status = :status WHERE p.id = :id")
    void updateStatusById(@Param("id") String id, @Param("status") Status status);

    List<Pedido> findAllByUsuarioId(String usuarioId);

    List<Pedido> findAllByUsuarioEmail(String email);

    @Query("SELECT new br.com.fs.ecommerce.foursalesecommerce.dto.TopCompradorDto(u.id, u.nome, COUNT(p.id), SUM(p.subtotal)) " +
            "FROM Pedido p JOIN p.usuario u " +
            "GROUP BY u.id, u.nome " +
            "ORDER BY 4 DESC")
    List<TopCompradorDto> findTopCompradores(Pageable pageable);

    @Query("SELECT new br.com.fs.ecommerce.foursalesecommerce.dto.TicketMedioDto(u.id, u.nome, AVG(p.subtotal)) " +
            "FROM Pedido p JOIN p.usuario u " +
            "GROUP BY u.id, u.nome")
    List<TicketMedioDto> findTicketMedioPorUsuario(Pageable pageable);

    @Query("SELECT SUM(p.subtotal) " +
            "FROM Pedido p " +
            "WHERE MONTH(p.criadoEm) = :mes AND YEAR(p.criadoEm) = :ano")
    BigDecimal findValorFaturadoPorMesEAno(@Param("mes") String mes, @Param("ano") String ano);
}
