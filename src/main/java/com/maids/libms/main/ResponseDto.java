package com.maids.libms.main;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder @Accessors(chain = true, fluent = true)
@JsonInclude @JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDto<T> {
    public T data;
    public String message;
    public List<String> errors = new ArrayList<>();

    final static HttpStatus defaultStatus = HttpStatus.OK;

    public static <T> ResponseEntity<Object> exceptionResponse(
            HttpStatus status, String... errorMessages) {
        ResponseDto<T> responseDto = new ResponseDto<T>().errors(List.of(errorMessages));
        return ResponseEntity.status(status).body(responseDto);
    }

    public static <T> ResponseEntity<ResponseDto<T>> response(T data, HttpStatus status, String message) {
        ResponseDto<T> responseDto = new ResponseDto<T>().data(data).message(message);
        return ResponseEntity.status(status).body(responseDto);
    }

    public static <T> ResponseEntity<ResponseDto<T>> response(T data, HttpStatus status) {
        return response(data, status, status.name());
    }

    public static <T> ResponseEntity<ResponseDto<T>> response(T data) {
        return response(data, defaultStatus);
    }
}
