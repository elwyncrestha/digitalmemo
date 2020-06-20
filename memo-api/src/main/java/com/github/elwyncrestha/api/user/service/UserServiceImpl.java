package com.github.elwyncrestha.api.user.service;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    private final UserRepository repository;

    protected UserServiceImpl(
        UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    protected BaseSpecBuilder<User> getSpec(Map<String, String> filterParams) {
        return new UserSpecBuilder(filterParams);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findUserByUsername(username);
    }

    @Override
    public User getAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            User user = (User) authentication.getPrincipal();
            user = this.findByUsername(user.getUsername());
            return user;
        } else {
            throw new UsernameNotFoundException(
                "User is not authenticated; Found of type " + authentication.getPrincipal()
                    .getClass() + "; Expected type User");
        }
    }
}
