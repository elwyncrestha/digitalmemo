package com.github.elwyncrestha.api.user.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.elwyncrestha.core.repository.BaseRepository;
import com.github.elwyncrestha.api.user.entity.User;

/**
 * @author Elvin Shrestha on 6/14/2020
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    User findUserByUsername(String username);

    List<User> findByRoleId(Long roleId);
}
