package com.arkisoftware.tutofast.tutors.command.application.handlers;

import com.arkisoftware.tutofast.tutors.contracts.events.TutorRegistered;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import com.arkisoftware.tutofast.tutors.contracts.events.TutorEdited;
import com.arkisoftware.tutofast.tutors.command.infra.*;

import java.util.Optional;

@Component
@ProcessingGroup("tutorDni")
public class TutorEventHandler {
    private final TutorDniRepository tutorDniRepository;

    public TutorEventHandler(TutorDniRepository tutorDniRepository) {
        this.tutorDniRepository = tutorDniRepository;
    }

    @EventHandler
    public void on(TutorRegistered event) {
        tutorDniRepository.save(new TutorDni(event.getDni(), event.getTutorId()));
    }

    @EventHandler
    public void on(TutorEdited event) {
        Optional<TutorDni> TutorDniOptional = tutorDniRepository.getDniByTutorId(event.getTutorId());
        TutorDniOptional.ifPresent(tutorDniRepository::delete);
        tutorDniRepository.save(new TutorDni(event.getDni(), event.getTutorId()));
    }
}
