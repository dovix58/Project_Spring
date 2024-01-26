package com.example.springproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @JsonManagedReference
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE) private List<Photo> photos;

    public void setPhotos(List<Photo> photos) {
        if(photos != null){
            this.photos.addAll(photos);
        }
    }
}
