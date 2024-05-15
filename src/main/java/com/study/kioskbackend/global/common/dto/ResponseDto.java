package com.study.kioskbackend.global.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpHeaders;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {
    private boolean success;
    private T data;
    private Error error;

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(true, data, null);
    }

    public static <T> ResponseDto<T> successHeader(T data, HttpHeaders headers) {
        return new ResponseDto<>(true, data, null);
    }

    public static <T> ResponseDto<T> successWithNoData() {
        return new ResponseDto<>(true, null, null);
    }

    public static <T> ResponseDto<T> fail(String code, String message) {
        return new ResponseDto<>(false, null, new Error(code, message));
    }

    @Getter
    @AllArgsConstructor
    static class Error {
        private String code;
        private String message;

    }
}