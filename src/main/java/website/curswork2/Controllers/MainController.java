package website.curswork2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import website.curswork2.models.Home;
import website.curswork2.repositories.HomeRepository;


@Controller
public class MainController {

    @Autowired
    private HomeRepository homeRepository;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Home> home = homeRepository.findAll();
        model.addAttribute("home", home);
        return "index";
    }
}