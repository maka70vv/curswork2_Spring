package website.curswork2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import website.curswork2.models.News;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByTitle(String title);
}
