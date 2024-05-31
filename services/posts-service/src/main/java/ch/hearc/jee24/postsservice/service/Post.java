package ch.hearc.jee24.postsservice.service;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Post {
    public Post() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String publisherId;
    private LocalDate published;
    private String imagePath;

    @Column(columnDefinition="TEXT")
    private String content;
}
