package com.example.carservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="reviews")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    private User author;
}