package repository_spring.repository_spring.domain.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import repository_spring.repository_spring.domain.entity.Cliente;
import repository_spring.repository_spring.domain.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
  Set<Pedido> findByCliente(Cliente cliente);
}
