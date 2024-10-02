package com.coffeemachine.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "drinks")
@Getter
@Setter
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "drink", cascade = CascadeType.ALL)
    private Set<Receipt> receiptSet;

    @OneToOne(optional=false, mappedBy="drink")
    private Rating rating;

    public Drink(String name, Rating rating) {
        this.name = name;
        this.rating = rating;
    }
}
