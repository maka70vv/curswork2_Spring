package website.curswork2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import website.curswork2.models.Merch;

import java.util.List;

public interface MerchRepository extends JpaRepository<Merch, Long> {
    List<Merch> findByTitle(String title);
}
