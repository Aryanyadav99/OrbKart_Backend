package com.eshop.Ecommerce.Repositories;

import com.eshop.Ecommerce.Model.AppRole;
import com.eshop.Ecommerce.Model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String username);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);


    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.roleName= :role")
    Page<User> findByRoleName(@Param("role") AppRole appRole, Pageable pageDetails);
}
