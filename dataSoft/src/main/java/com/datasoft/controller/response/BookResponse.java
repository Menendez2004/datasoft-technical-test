package com.datasoft.controller.response;

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
public class BookResponse {
    private UUID id;
    private String name;
    private String summary;
    private BigDecimal price;
    private String state;
    private String image;
    private GenreResponse genre;
}
