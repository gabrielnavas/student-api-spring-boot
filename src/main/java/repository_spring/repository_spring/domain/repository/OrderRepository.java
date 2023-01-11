package repository_spring.repository_spring.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import repository_spring.repository_spring.domain.entity.Client;
import repository_spring.repository_spring.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
  List<Order> findByClient(Client client);
}
