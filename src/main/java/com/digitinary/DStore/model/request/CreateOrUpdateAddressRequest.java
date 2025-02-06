package com.digitinary.DStore.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// TODO: Separate update and create request
@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateOrUpdateAddressRequest(
        @JsonProperty @NotBlank String title,
        @JsonProperty @NotBlank String country,
        @JsonProperty @NotBlank String city,
        @JsonProperty @NotBlank String street,
        @JsonProperty @NotNull Integer buildingNumber
) {
}
