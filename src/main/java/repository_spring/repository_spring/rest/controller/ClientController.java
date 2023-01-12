package repository_spring.repository_spring.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import repository_spring.repository_spring.domain.entity.Client;
import repository_spring.repository_spring.domain.repository.ClientRepository;
import repository_spring.repository_spring.service.order.exceptions.ClientNotFoundException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController()
@RequestMapping("/api/clients")
public class ClientController {

  @Autowired
  private ClientRepository clientRepository;

  @GetMapping("/{id}")
  public Client getClientById(@PathVariable Integer id) {
    return clientRepository
        .findById(id)
        .orElseThrow(() -> new ClientNotFoundException(id));
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public Client save(@RequestBody @Valid Client client) {
    return clientRepository.save(client);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable Integer id, @RequestBody Client client) {
    Optional<Client> clientFound = clientRepository.findById(id);
    if(clientFound.isPresent()) {
      client.setId(clientFound.get().getId());
      clientRepository.save(client);
    } else {
      throw new ClientNotFoundException(id);
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Integer id) {
    Optional<Client> clientFound = clientRepository.findById(id);
    if(clientFound.isPresent()) {
      clientRepository.delete(clientFound.get());
    } else {
      throw new ClientNotFoundException(id);
    }
  }

  @GetMapping()
  public List<Client> find(Client client) {
    ExampleMatcher matcher = ExampleMatcher
    .matchingAny() //matching or
    .withIgnoreCase() // ignora as caixas altas e baixas nas strings
    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // tipo um link
    
    Example<Client> example = Example.of(client, matcher);
    return clientRepository.findAll(example);
  }
}
