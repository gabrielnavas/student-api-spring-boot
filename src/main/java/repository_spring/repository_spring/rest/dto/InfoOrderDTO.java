package repository_spring.repository_spring.rest.dto;

import java.util.List;

import lombok.Builder;

@Builder
public class InfoOrderDTO {
  private Integer id;
  private String cpf;
  private String nameClient;
  private Double total;
  private String createdAtOrder;
  private String status;
  private List<InfoItemOrderDTO> items;
  

  public InfoOrderDTO() { }

  public InfoOrderDTO(Integer id, String cpf, String nameClient, Double total, String createdAtOrder, String status,
      List<InfoItemOrderDTO> items) {
    this.id = id;
    this.cpf = cpf;
    this.nameClient = nameClient;
    this.total = total;
    this.createdAtOrder = createdAtOrder;
    this.items = items;
    this.status=status;
  }
  

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getNameClient() {
    return nameClient;
  }

  public void setNameClient(String nameClient) {
    this.nameClient = nameClient;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public List<InfoItemOrderDTO> getItems() {
    return items;
  }

  public void setItems(List<InfoItemOrderDTO> items) {
    this.items = items;
  }

  public String getCreatedAtOrder() {
    return createdAtOrder;
  }

  public void setCreatedAtOrder(String createdAtOrder) {
    this.createdAtOrder = createdAtOrder;
  }
  
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
