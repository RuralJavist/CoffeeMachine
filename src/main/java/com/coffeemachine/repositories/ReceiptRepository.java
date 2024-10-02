package com.coffeemachine.repositories;

import com.coffeemachine.entities.Receipt;
import com.coffeemachine.entities.RecipeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, RecipeKey> {


}