package com.github.elwyncrestha.digitalmemo.user.repository;

import org.springframework.stereotype.Repository;

import com.github.elwyncrestha.digitalmemo.repository.BaseRepository;
import com.github.elwyncrestha.digitalmemo.user.entity.User;

/**
 * @author Elvin Shrestha on 6/14/2020
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long> {

}
