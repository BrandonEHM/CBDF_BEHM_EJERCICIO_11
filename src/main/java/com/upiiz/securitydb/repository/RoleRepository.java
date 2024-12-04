package com.upiiz.securitydb.repository;

import com.upiiz.securitydb.entities.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RolEntity, Long> {
}