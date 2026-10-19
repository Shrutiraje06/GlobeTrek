package com.indiatour.etour.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indiatour.etour.entity.ERole;
import com.indiatour.etour.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);

}