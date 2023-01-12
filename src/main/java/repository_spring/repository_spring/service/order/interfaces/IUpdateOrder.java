package repository_spring.repository_spring.service.order.interfaces;

import repository_spring.repository_spring.domain.entity.StatusOrder;

public interface IUpdateOrder {
  void updateStatusOrder(Integer orderId, StatusOrder statusOrder);
}
