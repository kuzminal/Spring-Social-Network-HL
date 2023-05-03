package ru.kuzmin.socialnethl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kuzmin.socialnethl.api.UserApi;
import ru.kuzmin.socialnethl.model.UserDto;
import ru.kuzmin.socialnethl.model.UserRegisterPost200ResponseDto;
import ru.kuzmin.socialnethl.model.UserRegisterPostRequestDto;
import ru.kuzmin.socialnethl.repository.UserRepository;

import java.util.Optional;

@RestController
@Slf4j
public class UserController implements UserApi {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<UserDto> userGetIdGet(String id) {
        var user = userRepository.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserRegisterPost200ResponseDto> userRegisterPost(UserRegisterPostRequestDto userRegisterPostRequestDto) {
        var registerResp = Optional.of(userRepository.registerUser(userRegisterPostRequestDto));
        return registerResp.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).build());
    }
}
