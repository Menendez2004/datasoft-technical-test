package com.datasoft.controller.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateRequest {

    @Size(max = 50, message = "The book name cannot exceed 50 characters")
    private String name;

    @Size(max = 500, message = "The summary cannot exceed 500 characters")
    private String summary;

    @DecimalMin(value = "0.0", message = "The price must be greater than or equal to 0")
    private BigDecimal price;

    @Size(max = 10, message = "The state cannot exceed 10 characters")
    private String state;

    @Size(max = 500, message = "The image path cannot exceed 500 characters")
    private String image;

    private UUID genreId;

    private UUID userId;
}
