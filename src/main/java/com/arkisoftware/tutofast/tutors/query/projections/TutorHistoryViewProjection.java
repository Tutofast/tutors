package com.arkisoftware.tutofast.tutors.query.projections;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;
import com.arkisoftware.tutofast.tutors.command.domain.*;
import com.arkisoftware.tutofast.tutors.contracts.events.*;


import java.time.Instant;
import java.util.Optional;

@Component
public class TutorHistoryViewProjection {
    private final TutorHistoryViewRepository tutorHistoryViewRepository;

    public TutorHistoryViewProjection(TutorHistoryViewRepository tutorHistoryViewRepository) {
        this.tutorHistoryViewRepository = tutorHistoryViewRepository;
    }

    @EventHandler
    public void on(TutorRegistered event, @Timestamp Instant timestamp) {
        TutorHistoryView tutorHistoryView = new TutorHistoryView(event.getTutorId(), event.getFirstName(), event.getLastName(), event.getDni(), TutorStatus.ACTIVE.toString(), event.getOccurredOn());
        tutorHistoryViewRepository.save(tutorHistoryView);
    }

    @EventHandler
    public void on(TutorEdited event, @Timestamp Instant timestamp) {
        Optional<TutorHistoryView> tutorHistoryViewOptional = tutorHistoryViewRepository.getLastByTutorId(event.getTutorId());
        if (tutorHistoryViewOptional.isPresent()) {
            TutorHistoryView tutorHistoryView = tutorHistoryViewOptional.get();
            tutorHistoryView = new TutorHistoryView(tutorHistoryView);
            tutorHistoryView.setFirstName(event.getFirstName());
            tutorHistoryView.setLastName(event.getLastName());
            tutorHistoryView.setDni(event.getDni());
            tutorHistoryView.setCreatedAt(event.getOccurredOn());
            tutorHistoryViewRepository.save(tutorHistoryView);
        }
    }
}
