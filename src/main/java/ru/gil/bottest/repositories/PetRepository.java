package ru.gil.bottest.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gil.bottest.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
