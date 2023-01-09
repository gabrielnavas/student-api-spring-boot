package repository_spring.repository_spring.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import repository_spring.repository_spring.domain.entity.Client;
import repository_spring.repository_spring.domain.repository.ClientRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController()
@RequestMapping("/api/clients")
public class ClientController {

  @Autowired
  private ClientRepository clientRepository;

  @GetMapping("/{id}")
  public Client getClientById(@PathVariable Integer id) {
    try {
      return clientRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "client not found"));
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
  public Client save(@RequestBody Client client) {
    try {
      return clientRepository.save(client);
    }
    catch(Exception ex) {
      System.out.println(ex); // utilizar um logger depois
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "server error");
    }
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable Integer id, @RequestBody Client client) {
    try {
      Optional<Client> clientFound = clientRepository.findById(id);
      if(clientFound.isPresent()) {
        client.setId(clientFound.get().getId());
        clientRepository.save(client);
      } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "client not found");
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
      Optional<Client> clientFound = clientRepository.findById(id);
      if(clientFound.isPresent()) {
        clientRepository.delete(clientFound.get());
      } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "client not found");
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
  public List<Client> find(Client client) {
    try {
      ExampleMatcher matcher = ExampleMatcher
        .matchingAny() //matching or
        .withIgnoreCase() // ignora as caixas altas e baixas nas strings
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // tipo um link
        
      Example<Client> example = Example.of(client, matcher);
      return clientRepository.findAll(example);
    }
    catch(Exception ex) {
      System.out.println(ex); // utilizar um logger depois
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "server error");
    }
  }
}
