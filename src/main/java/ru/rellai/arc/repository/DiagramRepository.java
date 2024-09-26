package ru.rellai.arc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rellai.arc.entity.Diagram;

public interface DiagramRepository extends JpaRepository<Diagram, Long> {
}