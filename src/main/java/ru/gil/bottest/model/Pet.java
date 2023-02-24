package ru.gil.bottest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean adopted;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id")
    private Report report;

}
