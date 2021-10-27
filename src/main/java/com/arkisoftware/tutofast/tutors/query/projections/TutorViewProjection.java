package com.arkisoftware.tutofast.tutors.query.projections;

import com.arkisoftware.tutofast.tutors.contracts.events.TutorEdited;
import com.arkisoftware.tutofast.tutors.contracts.events.TutorRegistered;
import com.arkisoftware.tutofast.tutors.command.domain.TutorStatus;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TutorViewProjection {
    private final TutorViewRepository tutorViewRepository;

    public TutorViewProjection(TutorViewRepository tutorViewRepository) {
        this.tutorViewRepository = tutorViewRepository;
    }

    @EventHandler
    public void on(TutorRegistered event, @Timestamp Instant timestamp) {
        TutorView tutorView = new TutorView(event.getTutorId(), event.getFirstName(), event.getLastName(), event.getDni(), TutorStatus.ACTIVE.toString(), event.getOccurredOn());
        tutorViewRepository.save(tutorView);
    }

    @EventHandler
    public void on(TutorEdited event, @Timestamp Instant timestamp) {
        Optional<TutorView> tutorViewOptional = tutorViewRepository.findById(event.getTutorId().toString());
        if (tutorViewOptional.isPresent()) {
            TutorView tutorView = tutorViewOptional.get();
            tutorView.setFirstName(event.getFirstName());
            tutorView.setLastName(event.getLastName());
            tutorView.setDni(event.getDni());
            tutorView.setUpdatedAt(event.getOccurredOn());
            tutorViewRepository.save(tutorView);
        }
    }
}