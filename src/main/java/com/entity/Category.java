package com.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "categories3")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String categoryName;
    private String categorycode;
    private String images;
    private boolean Status;
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;  // N Category thuộc 1 User
    @OneToMany(mappedBy = "category")
    private List<Videos> videos;

}

