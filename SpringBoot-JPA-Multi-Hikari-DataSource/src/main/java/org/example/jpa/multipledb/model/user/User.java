package org.example.jpa.multipledb.model.user;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @OneToMany
    List<Possession> possessionList;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    @Column(unique = true, nullable = false)
    private String email;
    private Integer status;

    @Override
    public String toString() {
        return "User [name=" + name + ", id=" + id + "]";
    }

}