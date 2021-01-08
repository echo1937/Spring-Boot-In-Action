package org.example.multi.one.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Address)实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    private static final long serialVersionUID = 617348776643182151L;

    private Long id;

    private String city;

    private String state;

    private String street;

    private String zipCode;

    private Long personId;


}