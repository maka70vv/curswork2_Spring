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
import website.curswork2.models.News;
import website.curswork2.services.NewsService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public String products(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("news", newsService.newsList(title));
        return "news";
    }


    @PostMapping("/news/create")
    public String createPost(@RequestParam("file1") MultipartFile file1, News news) throws IOException {
        newsService.saveNews(news, file1);
        return "redirect:/news";
    }

    @PostMapping("/news/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        newsService.deleteNews(id);
        return "redirect:/news";
    }

}