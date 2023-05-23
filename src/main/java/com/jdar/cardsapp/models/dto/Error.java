package com.jdar.cardsapp.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Error {

    @JsonProperty("status")
    private int httpStatus;

    @JsonProperty("cause")
    private String description;

    @JsonProperty("date")
    private LocalDateTime date;

}
