package repository_spring.repository_spring.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="order")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name="client_id")
  private Client client;
  
  @Column(name = "created_at")
  private LocalDate createdAt;

  @Column(name = "total", precision = 20, scale=2)
  private BigDecimal total;

  @OneToMany(mappedBy = "order")
  private Set<ItemOrder> itemsOrder;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public Set<ItemOrder> getItemsOrder() {
    return itemsOrder;
  }

  public void setItemsOrder(Set<ItemOrder> itemsOrder) {
    this.itemsOrder = itemsOrder;
  }
  
  
}
