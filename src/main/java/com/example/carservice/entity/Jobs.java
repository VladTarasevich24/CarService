package com.example.carservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private double cost;
    @ManyToMany(mappedBy = "jobs", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

}