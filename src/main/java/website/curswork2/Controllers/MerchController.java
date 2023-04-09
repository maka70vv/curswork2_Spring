package website.curswork2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import website.curswork2.models.Merch;
import website.curswork2.repositories.MerchRepository;


@Controller
public class MerchController {

    @Autowired
    private MerchRepository merchRepository;

    @GetMapping("/merchandise")
    public String home(Model model) {
        Iterable<Merch> merch = merchRepository.findAll();
        model.addAttribute("merch", merch);
        return "merch";
    }

}