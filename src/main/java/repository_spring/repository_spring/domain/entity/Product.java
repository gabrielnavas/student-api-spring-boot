package repository_spring.repository_spring.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id")
  private Integer id;

  @Column(name="description", length = 100)
  private String description;

  @Column(name="price", precision = 2, length = 20)
  private BigDecimal price;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public BigDecimal getPrice() {
    return price;
  }
  public void setPrice(BigDecimal price) {
    this.price = price;
  }
  
}
