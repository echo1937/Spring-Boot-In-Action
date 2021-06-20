package com.example.cache.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    @Id
    private Long id;
    private String lastName;
    private String email;
    private Integer gender; //性别 1男  0女
    @ManyToOne
    private Department department;
}
