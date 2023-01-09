package repository_spring.repository_spring.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import repository_spring.repository_spring.domain.entity.Cliente;
import repository_spring.repository_spring.domain.repository.Clientes;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController()
@RequestMapping("/api/clientes")
public class ClienteController {

  private Clientes clientes;

  public ClienteController(@Autowired Clientes clientes) {
    this.clientes=clientes;
  }

  @GetMapping("/{id}")
  public Cliente getClienteById(@PathVariable Integer id) {
    try {
      return clientes
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente not found"));
    }
    catch(Exception ex) {
      if(ex instanceof ResponseStatusException) {
        throw ex;
      }
      System.out.println(ex); // utilizar um logger depois
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "server error");
    }
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public Cliente save(@RequestBody Cliente cliente) {
    try {
      return clientes.save(cliente);
    }
    catch(Exception ex) {
      System.out.println(ex); // utilizar um logger depois
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "server error");
    }
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable Integer id, @RequestBody Cliente cliente) {
    try {
      Optional<Cliente> clienteFound = clientes.findById(id);
      if(clienteFound.isPresent()) {
        cliente.setId(clienteFound.get().getId());
        clientes.save(cliente);
      } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente not found");
      }
    }
    catch(Exception ex) {
      if(ex instanceof ResponseStatusException) {
        throw ex;
      }
      System.out.println(ex); // utilizar um logger depois
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "server error");
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Integer id) {
    try {
      Optional<Cliente> clienteFound = clientes.findById(id);
      if(clienteFound.isPresent()) {
        clientes.delete(clienteFound.get());
      } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente not found");
      }
    }
    catch(Exception ex) {
      if(ex instanceof ResponseStatusException) {
        throw ex;
      }
      System.out.println(ex); // utilizar um logger depois
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "server error");
    }
  }

  @GetMapping()
  public List<Cliente> find(Cliente cliente) {
    try {
      ExampleMatcher matcher = ExampleMatcher
        .matchingAny() //matching or
        .withIgnoreCase() // ignora as caixas altas e baixas nas strings
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // tipo um link
        
      Example<Cliente> example = Example.of(cliente, matcher);
      return clientes.findAll(example);
    }
    catch(Exception ex) {
      System.out.println(ex); // utilizar um logger depois
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "server error");
    }
  }
}
