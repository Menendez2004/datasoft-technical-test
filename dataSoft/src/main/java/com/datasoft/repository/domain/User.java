package com.datasoft.repository.domain;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name", length = 30, nullable = false)
    private String fullName;

    @Column(name = "username", length = 20, nullable = false, unique = true)
    private String username;

    @Column(name = "passwd", length = 100, nullable = false)
    private String password;

    @Column(name = "state", length = 3, nullable = false)
    private String state = "ACT";
}
