package website.curswork2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import website.curswork2.models.Home;

import java.util.List;

public interface HomeRepository extends JpaRepository<Home, Long> {
    List<Home> findByTitle(String title);
}
