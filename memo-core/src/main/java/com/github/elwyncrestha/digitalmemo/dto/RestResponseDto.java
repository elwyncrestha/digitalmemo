package com.github.elwyncrestha.digitalmemo.dto;

import static com.github.elwyncrestha.digitalmemo.constant.AppConstant.SUCCESSFUL;

import java.util.Optional;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Elvin Shrestha on 6/14/2020
 */
@Data
public class RestResponseDto {

    private String message;
    private Object detail;

    public ResponseEntity<?> success(Object detail) {
        RestResponseDto dto = new RestResponseDto();
        dto.setDetail(detail);
        dto.setMessage(SUCCESSFUL);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<?> fail(HttpStatus httpStatus, Optional<String> message) {
        RestResponseDto dto = new RestResponseDto();
        dto.setMessage(message.orElse(null));
        return new ResponseEntity<>(dto, httpStatus);
    }
}
