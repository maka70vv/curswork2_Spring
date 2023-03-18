package website.curswork2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import website.curswork2.models.Post;
import website.curswork2.repositories.PostRepository;

@Controller
public class ForumController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/forum")
    public String index(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "forum";
    }

}