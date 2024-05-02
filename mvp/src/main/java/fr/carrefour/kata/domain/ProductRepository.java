package fr.carrefour.kata.domain;

/**
 * @author Amine Achouri
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByBrand(@Param("brand") String brand);

    List<Product> findByColor(@Param("color") String color);
}