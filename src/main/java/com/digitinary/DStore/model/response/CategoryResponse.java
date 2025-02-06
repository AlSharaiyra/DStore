package com.digitinary.DStore.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record CategoryResponse(
        @JsonInclude Integer id,
        @JsonProperty String name
) {
}
