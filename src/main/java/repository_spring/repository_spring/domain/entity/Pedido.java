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
@Table(name="pedido")
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name="cliente_id")
  private Cliente cliente;
  
  @Column(name = "data_pedido")
  private LocalDate dataPedido;

  @Column(name = "total", precision = 20, scale=2)
  private BigDecimal total;

  @OneToMany(mappedBy = "pedido")
  private Set<ItemPedido> itensPedido;
  
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public Cliente getCliente() {
    return cliente;
  }
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }
  public LocalDate getDataPedido() {
    return dataPedido;
  }
  public void setDataPedido(LocalDate dataPedido) {
    this.dataPedido = dataPedido;
  }
  public BigDecimal getTotal() {
    return total;
  }
  public void setTotal(BigDecimal total) {
    this.total = total;
  }
  public Set<ItemPedido> getItensPedido() {
    return itensPedido;
  }
  public void setItensPedido(Set<ItemPedido> itensPedido) {
    this.itensPedido = itensPedido;
  }
  @Override
  public String toString() {
    return "Pedido [id=" + id + ", cliente=" + cliente + ", dataPedido=" + dataPedido + ", total=" + total + "]";
  }
  
  
}
