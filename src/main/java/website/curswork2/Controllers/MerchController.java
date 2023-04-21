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
import website.curswork2.models.Home;
import website.curswork2.models.Merch;
import website.curswork2.repositories.MerchRepository;
import website.curswork2.services.MerchService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class MerchController {
    private final MerchService merchService;

    @Autowired
    private MerchRepository merchRepository;


    @GetMapping("/merchandise")
    public String merch(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("merch", merchService.listMerch(title));
        return "merch";
    }

    @GetMapping("/merchandise/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        Merch product = merchService.getProductById(id);
        model.addAttribute("product", product);
        return "merch-info";
    }

    @PostMapping("/merchandise/create")
    public String createProduct(@RequestParam("file1") MultipartFile foto, Merch merch) throws IOException {
        merchService.saveProduct(merch, foto);
        return "redirect:/merchandise";
    }
    @GetMapping("/merchandise/{id}/edit")
    public String editElem(@PathVariable(value = "id") long id, Model model) {
        if (!merchRepository.existsById(id)) {
            return "redirect:/merchandise";
        }
        Optional<Merch> merch = merchRepository.findById(id);
        ArrayList<Merch> merchArrayList = new ArrayList<>();
        merch.ifPresent(merchArrayList::add);
        model.addAttribute("merch", merchArrayList);
        return "merch-edit";
    }
    @PostMapping("/merchandise/{id}/edit")
    public String editMerch(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String description, @RequestParam int cost, Model model) {
        Merch merch = merchRepository.findById(id).orElseThrow();
        merch.setTitle(title);
        merch.setDescription(description);
        merch.setCost(cost);
        merchRepository.save(merch);
        return "redirect:/merchandise";
    }

    @PostMapping("/merchandise/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        merchService.deleteProduct(id);
        return "redirect:/merchandise";
    }

}