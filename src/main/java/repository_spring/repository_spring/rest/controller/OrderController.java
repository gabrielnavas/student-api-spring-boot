package repository_spring.repository_spring.rest.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import repository_spring.repository_spring.domain.entity.ItemOrder;
import repository_spring.repository_spring.domain.entity.Order;
import repository_spring.repository_spring.rest.dto.InfoItemOrderDTO;
import repository_spring.repository_spring.rest.dto.InfoOrderDTO;
import repository_spring.repository_spring.rest.dto.OrderDTO;
import repository_spring.repository_spring.service.order.interfaces.IFindOrderService;
import repository_spring.repository_spring.service.order.interfaces.ISaveOrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  @Autowired
  private ISaveOrderService saveOrderService;
  
  @Autowired
  private IFindOrderService findOrderService;

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public Integer save(@RequestBody OrderDTO dto) {
    Order order = saveOrderService.save(dto);
    return order.getId();
  }

  @GetMapping("/{id}")
  public InfoOrderDTO findById(@PathVariable Integer id) {
    Order order = findOrderService.findById(id);
    return parserToInfoOrderDTO(order);
  }


  private InfoOrderDTO parserToInfoOrderDTO(Order order) {
    return InfoOrderDTO
      .builder()
      .cpf(order.getClient().getCpf())
      .id(order.getId())
      .total(order.getTotal())
      .nameClient(order.getClient().getName())
      .createdAtOrder(order.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
      .items(parserToInfoItemsOrderDTO(order.getItemsOrder()))
      .build();
  }

  private List<InfoItemOrderDTO> parserToInfoItemsOrderDTO(List<ItemOrder> itemsOrder) {
    if(CollectionUtils.isEmpty(itemsOrder)) {
      return Collections.emptyList();
    }

    return itemsOrder
      .stream()
      .map(itemOrder -> {
        return InfoItemOrderDTO
          .builder()
          .price(itemOrder.getPrice())
          .amout(itemOrder.getAmount())
          .descriptionProduct(itemOrder.getProduct().getDescription())
          .build();
      })
      .collect(Collectors.toList());
  }
}
