package com.datasoft.repository.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "summary", length = 500)
    private String summary;

    @Column(name = "price", precision = 6, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "state", length = 10, nullable = false)
    private String state = "AVAILABLE";

    @Column(name = "image", length = 500)
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gen_id", nullable = false)
    private Genre genre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_id", nullable = false)
    private User user;
}
