package repository_spring.repository_spring.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository_spring.repository_spring.domain.entity.Order;
import repository_spring.repository_spring.domain.repository.OrderRepository;
import repository_spring.repository_spring.service.order.exceptions.OrderNotFoundException;
import repository_spring.repository_spring.service.order.interfaces.IFindOrderService;


@Service
public class FindOrderService implements IFindOrderService {
  
  @Autowired
  private OrderRepository orderRepository;
  
  @Override
  public Order findById(Integer id) {
    // return null;
    return orderRepository
      .findByIdFetchItemsOrder(id)
      .orElseThrow(() -> new OrderNotFoundException(id));
  }
} 
