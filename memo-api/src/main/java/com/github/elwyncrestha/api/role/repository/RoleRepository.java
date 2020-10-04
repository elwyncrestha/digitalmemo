package com.github.elwyncrestha.api.role.repository;

import org.springframework.stereotype.Repository;

import com.github.elwyncrestha.api.role.entity.Role;
import com.github.elwyncrestha.core.repository.BaseRepository;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

}
