package repository_spring.repository_spring.rest.dto;

import java.util.List;


public class OrderDTO{
  public Integer clientId;
  public Double total;
  public List<ItemOrderDTO> itemsProduct;
  
  public OrderDTO() {}

  public OrderDTO(Integer clientId, Double total, List<ItemOrderDTO> itemsProduct) {
    this.clientId = clientId;
    this.total = total;
    this.itemsProduct = itemsProduct;
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

  public List<ItemOrderDTO> getItemsProduct() {
    return itemsProduct;
  }

  public void setItemsProduct(List<ItemOrderDTO> itemsProduct) {
    this.itemsProduct = itemsProduct;
  }
}

