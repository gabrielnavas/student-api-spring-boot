package repository_spring.repository_spring.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name="orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;
  
  @Column(name = "created_at")
  private LocalDate createdAt; //TODO testar com LocalDateTime

  @Column(name = "total", precision = 20, scale=2)
  @Min(value = 0_00)
  @Max(value = 1_000_000)
  private Double total;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="client_id")
  private Client client;

  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
  private List<ItemOrder> itemsOrder;

  public Order() { }

  public Order(LocalDate createdAt, @Min(0) @Max(1000000) Double total) {
    this.createdAt = createdAt;
    this.total = total;
  }

  public void addItemOrder(ItemOrder itemOrder) {
    if(itemsOrder == null) {
      itemsOrder = new ArrayList<>();
    }
    this.itemsOrder.add(itemOrder);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public List<ItemOrder> getItemsOrder() {
    return itemsOrder;
  }

  public void setItemsOrder(List<ItemOrder> itemsOrder) {
    this.itemsOrder = itemsOrder;
  }

  @Override
  public String toString() {
    return "Order [id=" + id + ", createdAt=" + createdAt + ", total=" + total + ", client=" + client + "]";
  }
}
