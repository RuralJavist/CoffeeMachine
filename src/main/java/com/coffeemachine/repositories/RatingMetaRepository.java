package com.coffeemachine.repositories;

import com.coffeemachine.entities.RatingMetadata;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface RatingMetaRepository extends SimpleRepository<RatingMetadata, Long>{

}
