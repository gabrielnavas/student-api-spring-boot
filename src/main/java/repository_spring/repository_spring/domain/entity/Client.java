package repository_spring.repository_spring.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
// @Table(name="client", schema="public")
@Table(name="client") // -> so quando o nome é diferente na tabela
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id") // -> so quando o nome é diferente na coluna
  private Integer id;
  
  @Column(name="name", length = 100)
  private String name;

  @Column(name="cpf", length = 11)
  private String cpf;

  @OneToMany(mappedBy = "client") // para fazer inner join, partindo de cliente
  @JsonIgnore //ignorar quando o parse pra json for feito
  private Set<Order> orders;

  public Client() {
  }

  public Client(Integer id, String name) {
    this.id = id;
    this.name = name;
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

  public Set<Order> getOrders() {
    return orders;
  }

  public void setOrders(Set<Order> orders) {
    this.orders = orders;
  } 

  @Override
  public String toString() {
    return "Client [id=" + id + ", name=" + name + "]";
  }
}
