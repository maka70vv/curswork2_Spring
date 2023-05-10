package website.curswork2.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import website.curswork2.models.Comment;
import website.curswork2.models.Image;
import website.curswork2.models.Post;
import website.curswork2.models.User;
import website.curswork2.repositories.CommentRepository;
import website.curswork2.repositories.ForumRepository;
import website.curswork2.repositories.UserRepository;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ForumService {

    private final ForumRepository forumRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
//Списька из многа постов и оно по имени найти можно
    public List<Post> postList(String title) {
        if (title != null) return forumRepository.findByTitle(title);
        return forumRepository.findAll();
    }

    public void savePost(Principal principal, Post post, MultipartFile file1) throws IOException {
        post.setUser(getUserByPrincipal(principal));
        Image image1;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            post.addImageToPost(image1);
        }
        Post postFromDb = forumRepository.save(post);
        postFromDb.setPreviewImageId(postFromDb.getImages().get(0).getId());
        forumRepository.save(post);
    }

    public void saveComment(Principal principal, Comment comment) {
        comment.setUser(getUserByPrincipal(principal));
        commentRepository.save(comment);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deletePost(Long id) {
        forumRepository.deleteById(id);
    }

    public Post getPostById(Long id) {
        return forumRepository.findById(id).orElse(null);
    }
}
