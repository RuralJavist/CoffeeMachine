package com.coffeemachine.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
@AllArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer tankRemainderMl;

    @OneToMany(mappedBy = "ingredient")
    private Set<Receipt> receipts;

    public final static Long BASE_TANK_CAPACITY_ML = 1000L;

    public Ingredient(String name) {
        this.name = name;
        this.tankRemainderMl = 1000;
    }

    public Ingredient() {
        this.tankRemainderMl = 1000;
    }


}
