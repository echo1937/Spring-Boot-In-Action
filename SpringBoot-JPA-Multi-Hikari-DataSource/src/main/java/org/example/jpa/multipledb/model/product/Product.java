package org.example.jpa.multipledb.model.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(schema = "products")
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
