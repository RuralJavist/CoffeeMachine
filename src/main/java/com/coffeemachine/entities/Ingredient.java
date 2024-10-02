package com.coffeemachine.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
@NoArgsConstructor
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

    public Ingredient(String name) {
        this.name = name;
        this.tankRemainderMl = 1000;
    }
}
