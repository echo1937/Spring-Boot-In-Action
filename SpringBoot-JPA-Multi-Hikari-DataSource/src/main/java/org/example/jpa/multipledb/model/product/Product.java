package org.example.jpa.multipledb.model.product;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private int id;
    private String name;
    private double price;

    public static Product from(int id, String name, double price) {
        return new Product(id, name, price);
    }


    @Override
    public String toString() {
        return "Product [name=" + name + ", id=" + id + "]";
    }
}
