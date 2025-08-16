package com.eshop.Ecommerce.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressid;

    @NotBlank
    @Size(min=5 ,message = "Street name must be atleast 5 characters")
    private String street;
    @NotBlank
    @Size(min=5 ,message = "Building name must be atleast 5 characters")
    private String buildingName;

    @NotBlank
    @Size(min=2 ,message = "city name must be atleast 2 characters")
    private String city;

    @NotBlank
    @Size(min=2 ,message = "Country name must be atleast 2 characters")
    private String country;

    @NotBlank
    @Size(min=5 ,message = "Pincode name must be atleast 5 characters")
    private String pincode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> users = new ArrayList<>();

    public Address( String street, String buildingName, String city, String country, String pincode) {
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.country = country;
        this.pincode = pincode;
    }
}
