package website.curswork2.repositories;

import org.springframework.data.repository.CrudRepository;
import website.curswork2.models.Post;

public interface PostRepository extends CrudRepository <Post, Long> {
}
