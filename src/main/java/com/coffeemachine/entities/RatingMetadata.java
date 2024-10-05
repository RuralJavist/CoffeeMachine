package com.coffeemachine.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "rating_meta")
@Getter
@Setter
public class RatingMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Instant cleanDate;

    public RatingMetadata() {
        this.cleanDate = Instant.now();
    }
}
