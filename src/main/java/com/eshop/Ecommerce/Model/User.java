package com.eshop.Ecommerce.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name="users"
        ,uniqueConstraints =
        {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userid;

    @NotBlank
    @Size(max = 20)
    @Column(name="username")
    private String userName;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name="email")
    private String email;

    @NotBlank
    @Size(max = 120)
    @Column(name="password")
    private String password;

    @Setter
    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
    fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            orphanRemoval=true)
    private Set<Product> products = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinTable(name = "user_address",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses = new ArrayList<>();

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Cart cart;


    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
