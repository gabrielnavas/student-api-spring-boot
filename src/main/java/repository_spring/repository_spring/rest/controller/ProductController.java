package repository_spring.repository_spring.rest.controller;

import java.util.List;

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

import repository_spring.repository_spring.domain.entity.Product;
import repository_spring.repository_spring.domain.repository.ProductRepository;
import repository_spring.repository_spring.service.order.exceptions.ProductNotFoundException;

@RestController()
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductRepository produtosRepository;

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public Product save(@RequestBody Product product) {
    try {
      return produtosRepository.save(product);
    } catch(Exception ex) {
      if(ex instanceof ResponseStatusException) {
        throw ex;
      }
      System.out.println(ex); // utilizar um logger depois
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "server error");
    }
  }

  @GetMapping("/{id}")
  public Product getById(@PathVariable Integer id) {
    return produtosRepository.findById(id)
      .orElseThrow(() -> new ProductNotFoundException(id));
  }

  @GetMapping()
  public List<Product> find(Product produto) {
    ExampleMatcher matcher = ExampleMatcher
      .matchingAny() //matching or
      .withIgnoreCase() // ignora as caixas altas e baixas nas strings
      .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // tipo um link
      
    Example<Product> example = Example.of(produto, matcher);
    return produtosRepository.findAll(example);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void atualizar(@PathVariable Integer id, Product produto) {
    produtosRepository.findById(id)
      .map(productFound -> {
        produto.setId(productFound.getId());
        produtosRepository.save(produto);
        return Void.TYPE;
      })
      .orElseThrow(() -> new ProductNotFoundException(id));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Integer id) {
    produtosRepository.findById(id).map(productFound -> {
      produtosRepository.delete(productFound);
      return Void.TYPE;
    })
    .orElseThrow(() -> new ProductNotFoundException(id));
  }
}
