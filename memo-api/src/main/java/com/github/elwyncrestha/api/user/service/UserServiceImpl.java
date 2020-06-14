package com.github.elwyncrestha.api.user.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.elwyncrestha.core.repository.BaseSpecBuilder;
import com.github.elwyncrestha.core.service.BaseServiceImpl;
import com.github.elwyncrestha.api.user.entity.User;
import com.github.elwyncrestha.api.user.repository.UserRepository;
import com.github.elwyncrestha.api.user.repository.spec.UserSpecBuilder;

/**
 * @author Elvin Shrestha on 6/14/2020
 */
@Service
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
