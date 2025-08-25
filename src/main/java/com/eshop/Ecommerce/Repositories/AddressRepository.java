package com.eshop.Ecommerce.Repositories;


import com.eshop.Ecommerce.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}