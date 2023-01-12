package repository_spring.repository_spring.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import repository_spring.repository_spring.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
  Order findByClient(Integer id);

  @Query(
    "select o " + 
    "from Order o " +
    "left join fetch o.itemsOrder " + 
    "where o.id = :id"
  )
  Optional<Order> findByIdFetchItemsOrder(Integer id);
}
