package com.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="videos")
public class Videos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int videoId;
    @Column(unique = true)
    private String title;
    private String poster;
    private int views;
    private String description;
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
