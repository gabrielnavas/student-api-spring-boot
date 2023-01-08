package repository_spring.repository_spring.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import repository_spring.repository_spring.domain.entity.Cliente;
import repository_spring.repository_spring.domain.repository.Clientes;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

  private Clientes clientes;

  public ClienteController(@Autowired Clientes clientes) {
    this.clientes=clientes;
  }

  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
    Optional<Cliente> clienteEncontrado = clientes.findById(id);
    if(clienteEncontrado.isPresent()) {
      return ResponseEntity.ok(clienteEncontrado.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping()
  @ResponseBody
  public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
    Cliente clienteCriado = clientes.save(cliente);
    ResponseEntity<Cliente> resp = new ResponseEntity<>(clienteCriado, HttpStatus.CREATED);
    return resp;
  }

  @PutMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Cliente> update(@PathVariable Integer id, @RequestBody Cliente cliente) {
    Optional<Cliente> clienteEncontrado = clientes.findById(id);
    if(clienteEncontrado.isPresent()) {
      Cliente clienteAtualizar = clienteEncontrado.get();
      clienteAtualizar.setNome(cliente.getNome());
      clientes.save(clienteAtualizar);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Cliente> delete(@PathVariable Integer id) {
    Optional<Cliente> clienteEncontrado = clientes.findById(id);
    if(clienteEncontrado.isPresent()) {
      clientes.delete(clienteEncontrado.get());
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping()
  @ResponseBody
  public ResponseEntity<List<Cliente>> getAll() {
    List<Cliente> clientesEncontrados = clientes.findAll();
    return ResponseEntity.ok(clientesEncontrados);
  }
}
