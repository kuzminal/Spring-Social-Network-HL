package ru.kuzmin.socialnethl.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.kuzmin.socialnethl.model.UserDto;
import ru.kuzmin.socialnethl.model.UserRegisterPost200ResponseDto;
import ru.kuzmin.socialnethl.model.UserRegisterPostRequestDto;
import ru.kuzmin.socialnethl.model.UserWithPasswordDTO;

import java.util.UUID;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDto getUserById(String id) {
        return jdbcTemplate.queryForObject("SELECT * from social.users where id = ?", new BeanPropertyRowMapper<>(UserDto.class), id);
    }

    public UserRegisterPost200ResponseDto registerUser(UserRegisterPostRequestDto userRegisterPostRequestDto) {
        return jdbcTemplate.queryForObject("INSERT INTO social.users (id, first_name, second_name, age, birthdate, biography, city, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id as userId",
                new BeanPropertyRowMapper<>(UserRegisterPost200ResponseDto.class),
                UUID.randomUUID(), userRegisterPostRequestDto.getFirstName(), userRegisterPostRequestDto.getSecondName(), userRegisterPostRequestDto.getAge() == null ? 0 : userRegisterPostRequestDto.getAge(), userRegisterPostRequestDto.getBirthdate(), userRegisterPostRequestDto.getBiography(),
                userRegisterPostRequestDto.getCity(), userRegisterPostRequestDto.getPassword());
    }

    public UserWithPasswordDTO getUserByIdWithPassord(String id) {
        return jdbcTemplate.queryForObject("SELECT id, password from social.users where id = ?", new BeanPropertyRowMapper<>(UserWithPasswordDTO.class), id);
    }
}
