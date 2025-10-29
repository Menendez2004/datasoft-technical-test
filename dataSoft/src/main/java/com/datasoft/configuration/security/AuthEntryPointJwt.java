package com.datasoft.configuration.security;

import com.datasoft.configuration.web.ApiError;
import com.datasoft.configuration.web.ApiErrorWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;


@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);
    private final ObjectMapper objectMapper;
    public AuthEntryPointJwt(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        logger.error("Unauthorized error at {}: {}",
                request.getRequestURI(), authException.getMessage());

        ApiError error = ApiError.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .type(authException.getClass().getSimpleName())
                .title(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .description(defaultMessage(authException))
                .source(request.getRequestURI())
                .build();

        ApiErrorWrapper wrapper = new ApiErrorWrapper();
        wrapper.addApiError(error);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        objectMapper.writeValue(response.getWriter(), wrapper);
    }

    private String defaultMessage(AuthenticationException ex) {
        String msg = ex.getMessage();
        return (msg == null || msg.isBlank())
                ? "Full authentication is required to access this resource"
                : msg;
    }
}