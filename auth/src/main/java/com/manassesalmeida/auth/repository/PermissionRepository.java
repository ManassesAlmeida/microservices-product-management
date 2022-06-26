package com.manassesalmeida.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.manassesalmeida.auth.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

	Permission findByDescription(@Param("description") String description);
}
