package com.github.elwyncrestha.api.user.repository;

import org.springframework.stereotype.Repository;

import com.github.elwyncrestha.core.repository.BaseRepository;
import com.github.elwyncrestha.api.user.entity.User;

/**
 * @author Elvin Shrestha on 6/14/2020
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    User findUserByUsername(String username);
}
