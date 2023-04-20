package website.curswork2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import website.curswork2.models.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}