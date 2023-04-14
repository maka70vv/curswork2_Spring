package website.curswork2.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import website.curswork2.models.Role;
import website.curswork2.models.User;
import website.curswork2.repositories.UserRepository;
import website.curswork2.services.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        userService.createUser(user);
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "User with email " + user.getEmail() + " is existed");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String userList(Model model) {
        model.addAttribute("user", userRepository.findAll());
        return "userList";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
//        model.addAttribute("posts", user.getPost());
        return "user-info";
    }


}