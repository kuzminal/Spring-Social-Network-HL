package ru.kuzmin.socialnethl.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserWithPasswordDTO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("password")
    private String password;
}
