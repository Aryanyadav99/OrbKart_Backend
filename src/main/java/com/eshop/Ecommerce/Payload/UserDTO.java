package com.eshop.Ecommerce.Payload;

import com.eshop.Ecommerce.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long userId;
    private String userName;
    private String email;
    private String password;
    private Set<Role> roles=new HashSet<>();
    private AddressDTO address;
    private CartDTO cart;
}
