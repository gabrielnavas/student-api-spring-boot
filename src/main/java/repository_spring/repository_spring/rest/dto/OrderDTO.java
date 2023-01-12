package repository_spring.repository_spring.rest.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import repository_spring.repository_spring.validation.NotEmptyList;


public class OrderDTO{

  @NotNull(message = "missing client id")
  private Integer clientId;

  @NotNull(message = "missing total")
  private Double total;

  @NotEmptyList(message = "items product cannot be empty")
  private List<ItemOrderDTO> itemsOrder;
  
  public OrderDTO() {}

  public OrderDTO(Integer clientId, Double total, List<ItemOrderDTO> itemsOrder) {
    this.clientId = clientId;
    this.total = total;
    this.itemsOrder = itemsOrder;
  }

  public Integer getClientId() {
    return clientId;
  }

  public void setClientId(Integer clientId) {
    this.clientId = clientId;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public List<ItemOrderDTO> getItemsOrder() {
    return itemsOrder;
  }

  public void setItemsOrder(List<ItemOrderDTO> itemsProduct) {
    this.itemsOrder = itemsProduct;
  }
}

