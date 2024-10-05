package com.coffeemachine.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "drinks")
@Getter
@Setter
@NoArgsConstructor
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @OneToMany(mappedBy = "drink", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receipt> receipts;

    @OneToOne(optional=false, mappedBy="drink", cascade = CascadeType.ALL)
    private Rating rating;

    public Drink(String name, Rating rating) {
        this.name = name;
        this.rating = rating;
        rating.setDrink(this);
    }
}
