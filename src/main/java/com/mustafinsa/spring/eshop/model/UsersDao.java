package com.mustafinsa.spring.eshop.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Component
public class UsersDao {

    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("dataSourceUser")
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Transactional
    public boolean create(User user) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", user.getUsername());
        params.addValue("password", passwordEncoder.encode(user.getPassword()));
        params.addValue("email", user.getEmail());
        params.addValue("enabled", user.isEnabled());
        params.addValue("authority", user.getAuthority());
        params.addValue("name", user.getName());

        jdbcTemplate.update("insert into users (username, email, password, enabled, name) values (:username, :email, :password, :enabled, :name)", params);

        jdbcTemplate.update("insert into authorities (username, authority) values (:username, :authority)", params);
        return true;
    }

    public boolean exists(String username) {
        return jdbcTemplate.queryForObject(
                "select count(*) from Users where username=:username", new MapSqlParameterSource("username", username), Integer.class) > 0;
    }

    public List<User> getAllUsers() {

        return jdbcTemplate.query("SELECT * FROM Users u, Authorities a " +
                "WHERE u.username=a.username", BeanPropertyRowMapper.newInstance(User.class));
    }
}
