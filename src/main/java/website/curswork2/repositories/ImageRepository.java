package website.curswork2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import website.curswork2.models.Image;

public interface ImageRepository extends JpaRepository <Image, Long>{
}
