package repository_spring.repository_spring.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import repository_spring.repository_spring.domain.entity.Order;
import repository_spring.repository_spring.rest.dto.OrderDTO;
import repository_spring.repository_spring.service.order.interfaces.ISaveOrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  private ISaveOrderService saveOrderService;

  public OrderController(ISaveOrderService saveOrderService) {
    this.saveOrderService = saveOrderService;
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public Integer save(@RequestBody OrderDTO dto) {
    Order order = saveOrderService.save(dto);
    return order.getId();
  }
}
