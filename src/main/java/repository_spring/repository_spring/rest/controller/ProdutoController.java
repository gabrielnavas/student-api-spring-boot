package repository_spring.repository_spring.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import repository_spring.repository_spring.domain.entity.Produto;
import repository_spring.repository_spring.domain.repository.Produtos;

@RestController()
@RequestMapping("/api/produtos")
public class ProdutoController {

  @Autowired
  private Produtos produtos;

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public Produto save(@RequestBody Produto produto) {
    try {
      return produtos.save(produto);
    } catch(Exception ex) {
      if(ex instanceof ResponseStatusException) {
        throw ex;
      }
      System.out.println(ex); // utilizar um logger depois
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "server error");
    }
  }

  @GetMapping("/{id}")
  public Produto getById(@PathVariable Integer id) {
    try {
      return produtos.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "producto not found"));
    } catch(Exception ex) {
      if(ex instanceof ResponseStatusException) {
        throw ex;
      }
      System.out.println(ex); // utilizar um logger depois
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "server error");
    }
  }

  @GetMapping()
  public List<Produto> find(Produto produto) {
    try {
      ExampleMatcher matcher = ExampleMatcher
        .matchingAny() //matching or
        .withIgnoreCase() // ignora as caixas altas e baixas nas strings
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // tipo um link
        
      Example<Produto> example = Example.of(produto, matcher);
      return produtos.findAll(example);
    } catch(Exception ex) {
      System.out.println(ex); // utilizar um logger depois
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "server error");
    }
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void atualizar(@PathVariable Integer id, Produto produto) {
    try {
      produtos.findById(id)
        .map(produtoEncontrado -> {
          produto.setId(id);
          produtos.save(produto);
          return produto;
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "produto not found"));
    } catch(Exception ex) {
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
      Optional<Produto> produtoEncontrado = produtos.findById(id);
      if(produtoEncontrado.isPresent()) {
        produtos.delete(produtoEncontrado.get());
      } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "produto not found");
      }
    } catch(Exception ex) {
      if(ex instanceof ResponseStatusException) {
        throw ex;
      }
      System.out.println(ex); // utilizar um logger depois
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "server error");
    }
  }
}
