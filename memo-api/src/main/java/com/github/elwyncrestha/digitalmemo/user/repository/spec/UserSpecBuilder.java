package com.github.elwyncrestha.digitalmemo.user.repository.spec;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.github.elwyncrestha.digitalmemo.repository.BaseSpecBuilder;
import com.github.elwyncrestha.digitalmemo.user.entity.User;

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
