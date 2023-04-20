package website.curswork2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import website.curswork2.models.Post;

import java.util.List;

public interface ForumRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitle(String title);
}