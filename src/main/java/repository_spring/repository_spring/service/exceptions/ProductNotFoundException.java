package repository_spring.repository_spring.service.exceptions;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(Integer productId) {
    super("product " + productId + " not found");
  }
}
