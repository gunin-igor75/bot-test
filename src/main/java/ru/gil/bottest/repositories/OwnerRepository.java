package ru.gil.bottest.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gil.bottest.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}