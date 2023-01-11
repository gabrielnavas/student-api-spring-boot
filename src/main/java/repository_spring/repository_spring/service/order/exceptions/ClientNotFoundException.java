package repository_spring.repository_spring.service.order.exceptions;

public class ClientNotFoundException extends RuntimeException {
  public ClientNotFoundException(Integer clientId) {
    super("client " + clientId + " not found");
  }
}