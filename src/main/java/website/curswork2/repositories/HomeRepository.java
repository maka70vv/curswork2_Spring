package website.curswork2.repositories;

import org.springframework.data.repository.CrudRepository;
import website.curswork2.models.Home;

public interface HomeRepository extends CrudRepository <Home, Long> {
}
