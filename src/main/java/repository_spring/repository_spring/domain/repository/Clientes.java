package repository_spring.repository_spring.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import repository_spring.repository_spring.domain.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer> {
  
  
  List<Cliente> findByNomeLike(String string); 

  // exemplo usando jql
  @Query(value = "select c from Cliente c where nome like :nome")
  List<Cliente> buscarPorNome(@Param("nome") String nome); 

  // exemplo usando sql
  @Query(value="select (select count(*) from cliente where nome = 'gabriel') > 0", nativeQuery = true)
  boolean existePorNome(String nome);

  boolean existsByNome(String nome);


  void deleteByNome(String nome);

  @Query("select c from Cliente c left join fetch c.pedidos where c.id = :id")
  Cliente findClienteFetchPedidos(@Param("id") Integer id);
}