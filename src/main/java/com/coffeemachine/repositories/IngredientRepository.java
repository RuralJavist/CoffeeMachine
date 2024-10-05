package com.coffeemachine.repositories;

import com.coffeemachine.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends SimpleRepository<Ingredient, Long> {

    Ingredient findByName(String name);

}