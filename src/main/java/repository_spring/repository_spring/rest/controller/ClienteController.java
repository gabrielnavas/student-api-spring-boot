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
    try {
      Optional<Cliente> clienteEncontrado = clientes.findById(id);
      if(clienteEncontrado.isPresent()) {
        return ResponseEntity.ok(clienteEncontrado.get());
      }
      return ResponseEntity.notFound().build();
    }
    catch(Exception ex) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping()
  @ResponseBody
  public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
    try {
      Cliente clienteCriado = clientes.save(cliente);
      ResponseEntity<Cliente> resp = new ResponseEntity<>(clienteCriado, HttpStatus.CREATED);
      return resp;
    }
    catch(Exception ex) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PutMapping("/{id}")
  @ResponseBody
  public ResponseEntity update(@PathVariable Integer id, @RequestBody Cliente cliente) {
    try {
      return clientes
        .findById(id)
        .map(clienteEncontrado -> {
          cliente.setId(clienteEncontrado.getId());
          clientes.save(cliente);
          return ResponseEntity.noContent().build();
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }
    catch(Exception ex) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public ResponseEntity delete(@PathVariable Integer id) {
    try {
      return clientes
        .findById(id)
        .map(clienteEncontrado -> {
          clientes.delete(clienteEncontrado);
          return ResponseEntity.noContent().build();
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }
    catch(Exception ex) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping()
  @ResponseBody
  public ResponseEntity<List<Cliente>> getAll() {
    try {
      List<Cliente> clientesEncontrados = clientes.findAll();
      return ResponseEntity.ok(clientesEncontrados);
    }
    catch(Exception ex) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
