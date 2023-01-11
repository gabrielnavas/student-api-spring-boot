package repository_spring.repository_spring;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import repository_spring.repository_spring.domain.entity.Client;
import repository_spring.repository_spring.domain.entity.ItemOrder;
import repository_spring.repository_spring.domain.entity.Order;
import repository_spring.repository_spring.domain.entity.Product;
import repository_spring.repository_spring.domain.repository.ClientRepository;
import repository_spring.repository_spring.domain.repository.ItemOrderRepository;
import repository_spring.repository_spring.domain.repository.OrderRepository;
import repository_spring.repository_spring.domain.repository.ProductRepository;


// database local http://localhost:8080/h2-console

@SpringBootApplication
public class RepositorySpringApplication {

	@Bean
	@Autowired
	public CommandLineRunner runTest(
		ClientRepository clientRepository,
		ProductRepository productRepository,
		OrderRepository orderRepository,
		ItemOrderRepository itemOrderRepository
	) {
		return args -> {

			// INSERT PRODUCT, ORDER, ITEM_ORDER, CLIENT
			Product product = new Product();
			Order order = new Order();
			ItemOrder itemOrder = new ItemOrder();
			Client client = new Client();

			productRepository.save(product);
			clientRepository.save(client);

			order.setClient(client);
			order.setTotal(123.12);
			order.setCreatedAt(LocalDate.now());

			orderRepository.save(order);

			itemOrder.setOrder(order);
			itemOrder.setPrice(123.12);
			itemOrder.setProduct(product);
			itemOrder.setAmount(1);

			itemOrderRepository.save(itemOrder);

			// FIND ALL ORDERS BY CLIENT

			try {
				List<Order> ordersFinded = orderRepository.findByClient(client);
				ordersFinded.forEach(System.out::println);
			}
			catch (Exception ex) {
				System.out.println(ex);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(RepositorySpringApplication.class, args);
	}

}
