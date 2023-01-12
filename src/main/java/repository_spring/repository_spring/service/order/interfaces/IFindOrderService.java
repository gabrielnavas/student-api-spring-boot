package repository_spring.repository_spring.service.order.interfaces;

import repository_spring.repository_spring.domain.entity.Order;

public interface IFindOrderService {
  Order findById(Integer id);
}
