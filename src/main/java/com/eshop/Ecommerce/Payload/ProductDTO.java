package com.eshop.Ecommerce.Payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long ProductID;
    private String ProductName;
    private String image;
    private Integer quantity;
    private double price;
    private double discount;
    private double specialPrice;
    private String description;


}
