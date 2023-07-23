package com.spring.BlogApp.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.BlogApp.Models.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}
