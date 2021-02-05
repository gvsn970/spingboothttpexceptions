package com.onpassive.springbootexceptionhttps.permissions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionModel, Long> {

	PermissionModel findByPermissionName(String permisssionName);
}