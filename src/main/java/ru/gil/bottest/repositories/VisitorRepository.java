package ru.gil.bottest.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gil.bottest.entity.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor,Long> {
}