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
    private RecipeKey recipeKey;

    @MapsId("drinkId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    @MapsId("ingredientId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Column(name = "percentage_ratio", nullable = false)
    private Integer percentageRatio;

    @Column(name = "tank_consumption", nullable = false)
    private Integer tankConsumptionMl;

    @Transient
    private final static int TANK_CONSUMPTION = 50;

    public Receipt(Drink drink, Ingredient ingredient, Integer percentageRatio) {
        this.drink = drink;
        this.ingredient = ingredient;
        this.percentageRatio = percentageRatio;
        this.tankConsumptionMl = TANK_CONSUMPTION;
    }

    public Receipt(Drink drink, Ingredient ingredient, Integer percentageRatio, Integer tankConsumptionMl) {
        this.drink = drink;
        this.ingredient = ingredient;
        this.percentageRatio = percentageRatio;
        this.tankConsumptionMl = tankConsumptionMl;
    }
}
