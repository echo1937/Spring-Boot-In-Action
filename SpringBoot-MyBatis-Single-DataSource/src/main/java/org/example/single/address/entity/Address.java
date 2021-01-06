package org.example.single.address.entity;

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
    private static final long serialVersionUID = -13395477847985897L;

    private Long id;

    private String city;

    private String state;

    private String street;

    private String zipCode;

    private Long personId;


}