package com.coffeemachine.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@AllArgsConstructor
public class Rating implements Comparable<Rating> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private Long rating;

    @OneToOne
    @JoinColumn(name="drink_id", unique=true, nullable=false, updatable=false)
    private Drink drink;

    public Rating() {
        this.rating = 0L;
    }

    @Override
    public int compareTo(Rating o) {
        return o.rating.compareTo(this.rating);
    }
}
