package com.jdar.cardsapp.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DataResponse<T> {

    private T data;
    private Error error;

}
