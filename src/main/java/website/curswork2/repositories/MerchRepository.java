package website.curswork2.repositories;

import org.springframework.data.repository.CrudRepository;
import website.curswork2.models.Merch;

public interface MerchRepository extends CrudRepository <Merch, Long> {
}
