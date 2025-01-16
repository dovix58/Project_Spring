package com.example.springproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

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


    private String location;

    private String description;

    private LocalDate date;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;

    public Photo(String name, String location, String description, LocalDate date, Post post) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.date = date;
        this.post = post;
    }


}
