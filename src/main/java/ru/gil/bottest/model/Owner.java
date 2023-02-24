package ru.gil.bottest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idChat;
    private String name;
    private LocalDateTime date;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_id")
    private Pet pet;

}
