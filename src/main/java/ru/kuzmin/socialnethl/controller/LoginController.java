package ru.kuzmin.socialnethl.controller;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kuzmin.socialnethl.api.LoginApi;
import ru.kuzmin.socialnethl.model.LoginPost200ResponseDto;
import ru.kuzmin.socialnethl.model.LoginPostRequestDto;
import ru.kuzmin.socialnethl.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
public class LoginController implements LoginApi {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<LoginPost200ResponseDto> loginPost(LoginPostRequestDto loginPostRequestDto) {
        var user = userRepository.getUserByIdWithPassord(loginPostRequestDto.getId());
        final String hashed = Hashing.sha256()
                .hashString(loginPostRequestDto.getPassword(), StandardCharsets.UTF_8)
                .toString();
        if (Objects.equals(user.getPassword(), hashed)) {
           var resp = new LoginPost200ResponseDto();
           resp.setToken(UUID.randomUUID().toString());
            return ResponseEntity.ok(resp);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
