package com.onpassive.springbootexceptionhttps.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesModel, Long>{

	RolesModel findByRoleName(String roleName);

}
