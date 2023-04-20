package website.curswork2.services;
import website.curswork2.models.Image;
import website.curswork2.models.Merch;
import website.curswork2.repositories.MerchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MerchService {
    private final MerchRepository merchRepository;

    public List<Merch> listMerch(String title) {
        if (title != null) return merchRepository.findByTitle(title);
        return merchRepository.findAll();
    }

    public void saveProduct(Merch merch, MultipartFile file) throws IOException {
        Image image;
        if (file.getSize() != 0) {
            image = toImageEntity(file);
            image.setPreviewImage(true);
            merch.addImageToProduct(image);
        }
        Merch productFromDb = merchRepository.save(merch);
        productFromDb.setImageId(productFromDb.getImages().get(0).getId());
        merchRepository.save(merch);
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

    public void deleteProduct(Long id) {
        merchRepository.deleteById(id);
    }

    public Merch getProductById(Long id) {
        return merchRepository.findById(id).orElse(null);
    }
}