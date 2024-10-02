package com.coffeemachine.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@Setter
public class RecipeKey implements Serializable {

    private Long drinkId;

    private Long ingredientId;

    public RecipeKey(Long drinkId, Long ingredientId) {
        this.drinkId = drinkId;
        this.ingredientId = ingredientId;
    }
}
