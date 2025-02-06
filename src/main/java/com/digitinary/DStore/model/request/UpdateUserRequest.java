package com.digitinary.DStore.model.request;

import com.digitinary.DStore.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UpdateUserRequest(
        @JsonProperty("firstName") String firstName,
        @JsonProperty("lastName") String lastName,
        @JsonProperty("email") @Email String email,
        @JsonProperty("age") Integer age,
        @JsonProperty("gender") Gender gender
) {
}
