package website.curswork2.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import website.curswork2.models.Image;
import website.curswork2.models.News;
import website.curswork2.repositories.NewsRepository;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    public List<News> newsList(String title) {
        if (title != null) return newsRepository.findByTitle(title);
        return newsRepository.findAll();
    }

    public void saveNews(News news, MultipartFile file) throws IOException {
        Image image;
        if (file.getSize() != 0) {
            image = toImageEntity(file);
            image.setPreviewImage(true);
            news.addImageToProduct(image);
        }
        News newsFromDb = newsRepository.save(news);
        newsFromDb.setImageId(newsFromDb.getImages().get(0).getId());
        newsRepository.save(news);
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

    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    public News getProductById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }
}