package repository_spring.repository_spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import repository_spring.repository_spring.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer> { }
