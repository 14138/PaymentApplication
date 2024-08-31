package com.demo.valantic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.valantic.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
  Role save(Role role);
}
