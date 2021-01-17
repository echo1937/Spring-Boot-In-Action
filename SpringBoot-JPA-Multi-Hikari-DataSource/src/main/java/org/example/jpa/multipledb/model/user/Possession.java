package org.example.jpa.multipledb.model.user;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Possession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public Possession(final String name) {
        super();
        this.name = name;
    }

    @Override
    public String toString() {
        return "Possession [id=" + id + ", name=" + name + "]";
    }

}
