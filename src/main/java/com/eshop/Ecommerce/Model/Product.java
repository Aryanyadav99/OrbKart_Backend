package com.eshop.Ecommerce.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @NotBlank
    @Size(min = 3, message = "name atleast has 3 characters")
    private String productName;
    @NotBlank
    @Size(min = 10, message = "description atleast has 10 characters")
    private String description;
    private Integer quantity;
    private String image;
    private double price;
    private double discount;
    private double specialPrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="seller_id")
    private User user;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<CartItem> products= new ArrayList<>();
}
