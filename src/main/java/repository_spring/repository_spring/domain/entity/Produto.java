package repository_spring.repository_spring.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id")
  private Integer id;

  @Column(name="descricao", length = 100)
  private String descricao;

  @Column(name="preco", precision = 2, length = 20)
  private BigDecimal preco;

  

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getDescricao() {
    return descricao;
  }
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  public BigDecimal getPreco() {
    return preco;
  }
  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }
  
}
