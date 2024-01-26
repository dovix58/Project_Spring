package com.example.springproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Lob
    byte[] content;

    private String location;


    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Photo(String name, String location, Post post) {
        this.name = name;
        this.location = location;
        this.post = post;
    }
}
