package repository_spring.repository_spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import repository_spring.repository_spring.domain.entity.ItemOrder;

public interface ItemsOrderRepository extends JpaRepository<ItemOrder, Integer> { }
