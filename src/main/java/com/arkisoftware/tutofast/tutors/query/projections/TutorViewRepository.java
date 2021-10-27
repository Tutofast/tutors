package com.arkisoftware.tutofast.tutors.query.projections;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TutorViewRepository extends JpaRepository<TutorView, String> {
    Optional<TutorView> getByDni(String dni);

    @Query(value = "SELECT * FROM tutor_view WHERE tutor_id <> :tutorId AND dni = :dni", nativeQuery = true)
    Optional<TutorView> getByTutorIdAndDni(String tutorId, String dni);
}
