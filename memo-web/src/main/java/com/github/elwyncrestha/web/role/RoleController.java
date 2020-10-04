package com.github.elwyncrestha.web.role;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.elwyncrestha.api.role.entity.Role;
import com.github.elwyncrestha.api.role.service.RoleService;
import com.github.elwyncrestha.core.controller.BaseController;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
@RestController
@RequestMapping(RoleController.URL)
@Slf4j
public class RoleController extends BaseController<Role, Long> {

    static final String URL = "/v1/roles";

    protected RoleController(
        RoleService service) {
        super(service, log.getClass());
    }
}
