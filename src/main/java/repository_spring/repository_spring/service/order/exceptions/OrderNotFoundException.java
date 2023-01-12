package repository_spring.repository_spring.service.order.exceptions;

public class OrderNotFoundException extends RuntimeException {
  public OrderNotFoundException(Integer orderId) {
    super("order " + orderId + " not found");
  }
}
