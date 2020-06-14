package com.github.elwyncrestha.digitalmemo.dao;


import static com.github.elwyncrestha.digitalmemo.constant.AppConstant.USERNAME;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;


/**
 * @author Elvin Shrestha on 6/14/2020
 */
@Component
public class UserDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserDao(
        NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Long getAuthenticatedUserId(String username) {
        String query = "SELECT id from [user] where username = :username";
        Map<String, Object> map = new HashMap<>();
        map.put(USERNAME, username);
        return namedParameterJdbcTemplate.queryForObject(query, map, Long.class);
    }
}
