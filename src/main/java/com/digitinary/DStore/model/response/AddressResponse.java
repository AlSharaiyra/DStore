package com.digitinary.DStore.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record AddressResponse(
        @JsonProperty Integer id,
        @JsonProperty String title,
        @JsonProperty String country,
        @JsonProperty String city,
        @JsonProperty String street,
        @JsonProperty Integer buildingNumber,
        @JsonProperty Integer user_id
) {
}
