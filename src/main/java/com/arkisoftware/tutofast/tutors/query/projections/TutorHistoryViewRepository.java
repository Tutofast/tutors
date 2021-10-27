package com.arkisoftware.tutofast.tutors.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorHistoryViewRepository extends JpaRepository<TutorHistoryView, String> {
    @Query(value = "SELECT * FROM tutor_history_view WHERE tutor_history_id = (SELECT MAX(tutor_history_id) FROM tutor_history_view WHERE tutor_id = :tutorId)", nativeQuery = true)
    Optional<TutorHistoryView> getLastByTutorId(String tutorId);

    @Query(value = "SELECT * FROM tutor_history_view WHERE tutor_id = :tutorId ORDER BY created_at", nativeQuery = true)
    List<TutorHistoryView> getHistoryByTutorId(String tutorId);
}
