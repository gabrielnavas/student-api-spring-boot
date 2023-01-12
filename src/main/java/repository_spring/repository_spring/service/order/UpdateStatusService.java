package repository_spring.repository_spring.service.order;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository_spring.repository_spring.domain.entity.StatusOrder;
import repository_spring.repository_spring.domain.repository.OrderRepository;
import repository_spring.repository_spring.service.order.exceptions.OrderNotFoundException;
import repository_spring.repository_spring.service.order.interfaces.IUpdateOrder;


@Service
public class UpdateStatusService implements IUpdateOrder {
  
  @Autowired
  private OrderRepository orderRepository;

  @Override
  @Transactional
  public void updateStatusOrder(Integer orderId, StatusOrder statusOrder) {
    orderRepository.findById(orderId)
      .map(orderFound -> {
        orderFound.setStatusOrder(statusOrder);
        orderRepository.save(orderFound);
        return Void.TYPE;
      })
      .orElseThrow(() -> new OrderNotFoundException(orderId));
  }
}