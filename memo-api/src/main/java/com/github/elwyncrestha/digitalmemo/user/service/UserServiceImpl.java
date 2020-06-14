package com.github.elwyncrestha.digitalmemo.user.service;

import java.util.Map;

import com.github.elwyncrestha.digitalmemo.repository.BaseSpecBuilder;
import com.github.elwyncrestha.digitalmemo.service.BaseServiceImpl;
import com.github.elwyncrestha.digitalmemo.user.entity.User;
import com.github.elwyncrestha.digitalmemo.user.repository.UserRepository;
import com.github.elwyncrestha.digitalmemo.user.repository.spec.UserSpecBuilder;

/**
 * @author Elvin Shrestha on 6/14/2020
 */
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    protected UserServiceImpl(
        UserRepository repository) {
        super(repository);
    }

    @Override
    protected BaseSpecBuilder<User> getSpec(Map<String, String> filterParams) {
        return new UserSpecBuilder(filterParams);
    }
}
