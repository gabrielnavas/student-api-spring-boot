package repository_spring.repository_spring.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import repository_spring.repository_spring.domain.entity.Order;
import repository_spring.repository_spring.rest.dto.OrderDTO;
import repository_spring.repository_spring.service.exceptions.ClientNotFoundException;
import repository_spring.repository_spring.service.exceptions.ProductNotFoundException;
import repository_spring.repository_spring.service.interfaces.ISaveOrderService;

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
    try {
      Order order = saveOrderService.save(dto);
      return order.getId();
    } 
    catch(ProductNotFoundException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    catch(ClientNotFoundException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    catch(Exception ex) {
      System.out.println(ex);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "server error");
    }
  }
}
