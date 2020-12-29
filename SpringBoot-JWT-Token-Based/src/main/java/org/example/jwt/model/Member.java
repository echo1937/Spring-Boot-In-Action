package org.example.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Column(unique = true)
    String username;

    String password;

    String name;

    @ElementCollection(fetch = FetchType.EAGER)
    Set<String> authorities = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
