package br.com.fs.ecommerce.foursalesecommerce.repository;

import br.com.fs.ecommerce.foursalesecommerce.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, String> {

}
