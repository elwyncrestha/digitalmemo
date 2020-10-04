package com.github.elwyncrestha.web.user;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.elwyncrestha.api.user.entity.User;
import com.github.elwyncrestha.api.user.service.UserService;
import com.github.elwyncrestha.core.controller.BaseController;
import com.github.elwyncrestha.core.dto.RestResponseDto;
import com.github.elwyncrestha.core.enums.Status;

/**
 * @author Elvin Shrestha on 6/20/2020
 */
@RestController
@RequestMapping(UserController.URL)
@Slf4j
public class UserController extends BaseController<User, Long> {

    static final String URL = "/v1/users";
    private final UserService service;

    protected UserController(
        UserService service
    ) {
        super(service, log.getClass());
        this.service = service;
    }

    @GetMapping("/authenticated")
    public ResponseEntity<?> getAuthenticated() {
        return new RestResponseDto().success(service.getAuthenticated());
    }

    @GetMapping("/changeStatus")
    public ResponseEntity<?> changeStatus(@RequestParam Long id, @RequestParam Status status) {
        User user = service.findOne(id).orElse(null);
        if (null == user) {
            return new RestResponseDto().fail(HttpStatus.NOT_FOUND, Optional.empty());
        }

        user.setStatus(status);
        return new RestResponseDto().success(service.save(user));
    }
}
