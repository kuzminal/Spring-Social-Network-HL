package ru.kuzmin.socialnethl.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class SessionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getSessionToken(String token) {
        String userId = null;
        try {
            userId = jdbcTemplate.queryForObject("SELECT user_id from social.session where token = ?", new BeanPropertyRowMapper<>(String.class), token);
        }catch (EmptyResultDataAccessException empty) {
            log.info("Session not found");
        }
        return userId; 
    }
}
