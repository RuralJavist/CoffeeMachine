package com.coffeemachine.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "receipts")
@NoArgsConstructor
@Getter
@Setter
public class Receipt {

    @EmbeddedId
    private RecipeKey id;

    @MapsId("drinkId")
    @ManyToOne
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    @MapsId("ingredientId")
    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Column(name = "percentage_ratio", nullable = false)
    private Integer percentageRatio;

    public Receipt(Drink drink, Ingredient ingredient, Integer percentageRatio) {
        this.drink = drink;
        this.ingredient = ingredient;
        this.percentageRatio = percentageRatio;
        this.id = new RecipeKey(drink.getId(), ingredient.getId());
    }
}
