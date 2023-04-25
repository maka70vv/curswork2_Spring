package website.curswork2.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import website.curswork2.models.Home;
import website.curswork2.models.Order;
import website.curswork2.repositories.HomeRepository;
import website.curswork2.repositories.OrderRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public List<Order> orderList(String title) {
        if (title != null) return orderRepository.findByTitle(title);
        return orderRepository.findAll();
    }

    public void saveOrder(Order order) {
        log.info("Saving new {}", order);
        orderRepository.save(order);
    }
    public void deleteProduct(Long id) {
        orderRepository.deleteById(id);
    }

}
