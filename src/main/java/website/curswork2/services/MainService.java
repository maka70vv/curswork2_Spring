package website.curswork2.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import website.curswork2.models.Home;
import website.curswork2.repositories.HomeRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainService {
    private final HomeRepository homeRepository;
    public List<Home> homeList(String title) {
        if (title != null) return homeRepository.findByTitle(title);
        return homeRepository.findAll();
    }

    public void saveElem(Home home) {
        log.info("Saving new {}", home);
        homeRepository.save(home);
    }

    public Home getElemById(Long id) {
        return homeRepository.findById(id).orElse(null);
    }

}
