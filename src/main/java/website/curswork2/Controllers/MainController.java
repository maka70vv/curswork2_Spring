package website.curswork2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import website.curswork2.models.Home;
import website.curswork2.models.Merch;
import website.curswork2.models.Post;
import website.curswork2.repositories.HomeRepository;
import website.curswork2.repositories.MerchRepository;
import website.curswork2.services.MainService;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;


@Controller
public class MainController {

    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private MerchRepository merchRepository;
    @Autowired
    private MainService mainService;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Home> home = homeRepository.findAll();
        Iterable<Merch> merch = merchRepository.findAll();
        model.addAttribute("home", home);
        model.addAttribute("merch", merch);
        return "index";
    }
    @PostMapping("/elem/create")
    public String createElem(Home home) {
        mainService.saveElem(home);
        return "redirect:/";
    }
    @GetMapping("/elem/{id}/edit")
    public String editElem(@PathVariable(value = "id") long id, Model model) {
        if (!homeRepository.existsById(id)) {
            return "redirect:/";
        }
        Optional<Home> home = homeRepository.findById(id);
        ArrayList<Home> homeArrayList = new ArrayList<>();
        home.ifPresent(homeArrayList::add);
        model.addAttribute("home", homeArrayList);
        return "index-edit";
    }
    @PostMapping("/elem/{id}/edit")
    public String editElem(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String text, Model model) {
        Home home = homeRepository.findById(id).orElseThrow();
        home.setTitle(title);
        home.setText(text);
        homeRepository.save(home);
        return "redirect:/";
    }
}