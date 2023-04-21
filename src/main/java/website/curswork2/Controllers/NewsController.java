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
import website.curswork2.models.Merch;
import website.curswork2.models.News;
import website.curswork2.repositories.NewsRepository;
import website.curswork2.services.NewsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsRepository newsRepository;

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

    @GetMapping("/news/{id}/edit")
    public String editElem(@PathVariable(value = "id") long id, Model model) {
        if (!newsRepository.existsById(id)) {
            return "redirect:/news";
        }
        Optional<News> news = newsRepository.findById(id);
        ArrayList<News> newsArrayList = new ArrayList<>();
        news.ifPresent(newsArrayList::add);
        model.addAttribute("news", newsArrayList);
        return "news-edit";
    }
    @PostMapping("/news/{id}/edit")
    public String editNews(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String description, Model model) {
        News news = newsRepository.findById(id).orElseThrow();
        news.setTitle(title);
        news.setDescription(description);
        newsRepository.save(news);
        return "redirect:/news";
    }

    @PostMapping("/news/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        newsService.deleteNews(id);
        return "redirect:/news";
    }

}