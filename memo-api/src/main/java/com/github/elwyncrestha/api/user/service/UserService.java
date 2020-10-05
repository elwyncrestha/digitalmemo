package com.github.elwyncrestha.api.user.service;

import java.util.List;

import com.github.elwyncrestha.core.service.BaseService;
import com.github.elwyncrestha.api.user.entity.User;

/**
 * @author Elvin Shrestha on 6/14/2020
 */
public interface UserService extends BaseService<User, Long> {

    User findByUsername(String username);

    User getAuthenticated();

    List<User> findByRoleId(Long roleId);

}
