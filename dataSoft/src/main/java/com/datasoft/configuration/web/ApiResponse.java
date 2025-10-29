package com.datasoft.configuration.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {
    private T data;
    private Meta meta;

    public ApiResponse(T data) {
        this.data = data;
        this.meta = new Meta();
    }

    public static <T> ApiResponse<T> dataOnly(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.data = data;
        return response;
    }

    public static class Meta {
        @Getter
        @Setter
        private LocalDateTime timestamp;

        public Meta() {
            this.timestamp = LocalDateTime.now();
        }
    }
}

