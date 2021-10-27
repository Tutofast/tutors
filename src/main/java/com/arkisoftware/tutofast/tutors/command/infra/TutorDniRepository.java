package com.arkisoftware.tutofast.tutors.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TutorDniRepository extends JpaRepository<TutorDni, String> {
    Optional<TutorDni> getDniByTutorId(String tutorId);

    @Query(value = "SELECT * FROM tutor_id WHERE tutor_id <> :tutorId AND dni = :dni LIMIT 1", nativeQuery = true)
    Optional<TutorDni> getByDniForDistinctTutorId(String dni, String tutorId);
}
