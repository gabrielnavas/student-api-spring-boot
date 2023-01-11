package repository_spring.repository_spring.service.interfaces;

import java.util.List;

import repository_spring.repository_spring.domain.entity.Order;
import repository_spring.repository_spring.rest.dto.ItemOrderDTO;
import repository_spring.repository_spring.rest.dto.OrderDTO;

public interface ISaveOrderService {
  Order save(OrderDTO dto);
  Order createOrder(OrderDTO orderDTO);
  Order addItemsOrder(Order order, List<ItemOrderDTO> itemsOrder);
}
