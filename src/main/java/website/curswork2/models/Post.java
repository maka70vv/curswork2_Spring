package website.curswork2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "anons", columnDefinition = "text")
    private String anons;
    @Column(name = "full_text", columnDefinition = "text")
    private String full_text;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "post")
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;

    private LocalDate dateOfCreated;


    @PrePersist
    private void init() {
        dateOfCreated = LocalDate.now();
    }

    public void addImageToPost(Image image) {
        image.setPost(this);
        images.add(image);
    }

}