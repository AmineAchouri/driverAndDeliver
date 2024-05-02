package fr.carrefour.kata.domain;

/**
 * @author Amine Achouri
 */

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByUsername(String username);
}