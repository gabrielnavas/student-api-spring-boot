package repository_spring.repository_spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import repository_spring.repository_spring.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> { }
