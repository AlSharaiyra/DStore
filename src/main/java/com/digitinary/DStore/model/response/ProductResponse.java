package com.digitinary.DStore.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record ProductResponse(
        @JsonProperty Integer id,
        @JsonProperty String name,
        @JsonProperty String description,
        @JsonProperty Double price,
        @JsonProperty Integer inStock,
        @JsonProperty
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        Timestamp lastModified,
        @JsonProperty Integer category_id
) {
}
