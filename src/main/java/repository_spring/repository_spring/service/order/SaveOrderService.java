package repository_spring.repository_spring.service.order;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository_spring.repository_spring.domain.entity.Client;
import repository_spring.repository_spring.domain.entity.ItemOrder;
import repository_spring.repository_spring.domain.entity.Order;
import repository_spring.repository_spring.domain.entity.Product;
import repository_spring.repository_spring.domain.entity.StatusOrder;
import repository_spring.repository_spring.domain.repository.ClientRepository;
import repository_spring.repository_spring.domain.repository.ItemOrderRepository;
import repository_spring.repository_spring.domain.repository.OrderRepository;
import repository_spring.repository_spring.domain.repository.ProductRepository;
import repository_spring.repository_spring.rest.dto.ItemOrderDTO;
import repository_spring.repository_spring.rest.dto.OrderDTO;
import repository_spring.repository_spring.service.order.exceptions.ClientNotFoundException;
import repository_spring.repository_spring.service.order.exceptions.ProductNotFoundException;
import repository_spring.repository_spring.service.order.interfaces.ISaveOrderService;


@Service
public class SaveOrderService implements ISaveOrderService{
  
  @Autowired
  private OrderRepository orderRepository;
  
  @Autowired
  private ItemOrderRepository itemOrderRepository;
  
  @Autowired
  private ClientRepository clientRepository;
  
  @Autowired
  private ProductRepository productRepository;


  @Override
  @Transactional
  public Order save(OrderDTO orderDTO) {
    Order order = createOrder(orderDTO);
    order = orderRepository.save(order);

    order = addItemsOrder(order, orderDTO.getItemsOrder());
    order.getItemsOrder().forEach(itemOrder -> itemOrderRepository.save(itemOrder));
    
    return order;
  }

  public Order createOrder(OrderDTO orderDTO) {
    Client client = clientRepository
      .findById(orderDTO.getClientId())
      .orElseThrow(() -> new ClientNotFoundException(orderDTO.getClientId()));

    Order order = new Order();
    order.setClient(client);
    order.setCreatedAt(LocalDate.now());
    order.setTotal(orderDTO.getTotal());
    order.setStatusOrder(StatusOrder.ACCOMPLISHED);
    return order;
  }

  public Order addItemsOrder(Order order, List<ItemOrderDTO> itemsOrder) {
    for(ItemOrderDTO itemOrderDTO: itemsOrder) {
      Product product =  productRepository
        .findById(itemOrderDTO.getProductId())
        .orElseThrow(() -> new ProductNotFoundException(itemOrderDTO.getProductId()));
      
      ItemOrder itemOrder = new ItemOrder();
      itemOrder.setAmount(itemOrderDTO.getAmount());
      itemOrder.setOrder(order);
      itemOrder.setPrice(itemOrderDTO.getPrice());
      itemOrder.setProduct(product);
      order.addItemOrder(itemOrder);
    }
    return order;
  }
} 
