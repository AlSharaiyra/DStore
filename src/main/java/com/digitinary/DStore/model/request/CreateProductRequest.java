package com.digitinary.DStore.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateProductRequest(
        @JsonProperty @NotBlank String name,
        @JsonProperty @NotBlank String description,
        @JsonProperty @NotNull Double price,
        @JsonProperty @NotNull Integer inStock
) {

}
