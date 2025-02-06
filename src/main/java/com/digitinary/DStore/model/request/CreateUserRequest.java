package com.digitinary.DStore.model.request;

import com.digitinary.DStore.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateUserRequest(
        @JsonProperty("firstName") @NotBlank String firstName,
        @JsonProperty("lastName") @NotBlank String lastName,
        @JsonProperty("email") @NotBlank @Email String email,
        @JsonProperty("password") @NotBlank
        @Size(min = 8, max = 20, message = "Password length must be between 8 and 20 characters long")
        String password,
        @JsonProperty("age") @NotNull Integer age,
        @JsonProperty("gender") @NotNull Gender gender
) {
}
