package website.curswork2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import website.curswork2.models.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByTitle(String title);
}