package com.github.elwyncrestha.api.role.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.elwyncrestha.api.role.entity.Role;
import com.github.elwyncrestha.api.role.repository.RoleRepository;
import com.github.elwyncrestha.core.repository.BaseSpecBuilder;
import com.github.elwyncrestha.core.service.BaseServiceImpl;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    protected RoleServiceImpl(
        RoleRepository repository) {
        super(repository);
    }

    @Override
    protected BaseSpecBuilder<Role> getSpec(Map<String, String> filterParams) {
        return null;
    }
}
