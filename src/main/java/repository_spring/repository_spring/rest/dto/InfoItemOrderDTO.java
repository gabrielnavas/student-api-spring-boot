package repository_spring.repository_spring.rest.dto;

import lombok.Builder;

@Builder
public class InfoItemOrderDTO {
  private String descriptionProduct;
  private Double price;
  private Integer amout;

  public InfoItemOrderDTO() { }

  public InfoItemOrderDTO(String descriptionProduct, Double price, Integer amout) {
    this.descriptionProduct = descriptionProduct;
    this.price = price;
    this.amout = amout;
  }

  public String getDescriptionProduct() {
    return descriptionProduct;
  }

  public void setDescriptionProduct(String descriptionProduct) {
    this.descriptionProduct = descriptionProduct;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getAmout() {
    return amout;
  }

  public void setAmout(Integer amout) {
    this.amout = amout;
  }
}
