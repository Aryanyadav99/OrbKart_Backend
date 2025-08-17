package com.eshop.Ecommerce.Repositories;

import com.eshop.Ecommerce.Model.AppRole;
import com.eshop.Ecommerce.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}
