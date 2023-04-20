package website.curswork2.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import website.curswork2.models.Comment;
import website.curswork2.models.Post;
import website.curswork2.repositories.CommentRepository;
import website.curswork2.services.ForumService;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ForumController {
    @Autowired
    private ForumService forumService;

    @Autowired
    private CommentRepository commentRepository;
    @GetMapping("/forum")
    public String products(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("posts", forumService.postList(title));
        model.addAttribute("user", forumService.getUserByPrincipal(principal));
        return "forum";
    }

    @GetMapping("/forum/post/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        model.addAttribute("post", forumService.getPostById(id));
        model.addAttribute("comment", commentRepository.findAll());
        return "post-info";
    }

    @PostMapping("/forum/post/create")
    public String createPost(@RequestParam("file1") MultipartFile file1, Principal principal, Post post) throws IOException {
        forumService.savePost(principal, post, file1);
        return "redirect:/forum";
    }

    @PostMapping("/forum/post/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        forumService.deletePost(id);
        return "redirect:/forum";
    }

    @PostMapping("/forum/post/{id}/comment")
    public String createPost(Principal principal, Comment comment) throws IOException {
        forumService.saveComment(principal, comment);
        return "redirect:/forum/post/{id}";
    }
}