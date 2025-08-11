package com.eshop.Ecommerce.Payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    // used to transfer the data of clint to db
    private Long categoryId;
    private String categoryName;
}
