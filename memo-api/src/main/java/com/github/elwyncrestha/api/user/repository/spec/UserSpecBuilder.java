package com.github.elwyncrestha.api.user.repository.spec;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.github.elwyncrestha.core.repository.BaseSpecBuilder;
import com.github.elwyncrestha.api.user.entity.User;

/**
 * @author Elvin Shrestha on 6/14/2020
 */
public class UserSpecBuilder extends BaseSpecBuilder<User> {

    public UserSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<User> getSpecification(String property, String filterValue) {
        return new UserSpec(property, filterValue);
    }
}
