package repository_spring.repository_spring.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="clients") // -> so quando o nome é diferente na tabela
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id") // -> so quando o nome é diferente na coluna
  private Integer id;
  
  @Column(name="name", length = 100)
  private String name;

  @Column(name="cpf", length = 11)
  private String cpf;

  @OneToMany(mappedBy = "client", fetch = FetchType.LAZY) // para fazer inner join, partindo de cliente
  private List<Order> orders;

  public Client() {
  }

  public Client(String name, String cpf) {
    this.name = name;
    this.cpf = cpf;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  @Override
  public String toString() {
    return "Client [id=" + id + ", name=" + name + ", cpf=" + cpf + "]";
  }
}
