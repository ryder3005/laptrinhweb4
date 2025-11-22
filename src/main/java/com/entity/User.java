package com.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users3")
public class User {

    @Id
    @Column( nullable = false, unique = true, length = 50)
    private String username;

    @Column( nullable = false)
    private String password;

    @Column( unique = true)
    private String email;

    
    private String fullName;

    @Column( length = 15)
    private String phone;

    
    private Boolean admin;

    
    private Boolean active;

    
    private String image;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Category> categories;
}