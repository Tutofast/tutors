package com.arkisoftware.tutofast.tutors.command.domain;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.arkisoftware.tutofast.tutors.contracts.commands.*;
import com.arkisoftware.tutofast.tutors.contracts.events.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;

@Aggregate
public class Tutor {
    @AggregateIdentifier
    private String tutorId;
    private String firstName;
    private String lastName;
    private String dni;
    private TutorStatus status;

    public Tutor() {}

    @CommandHandler
    public Tutor(RegisterTutor command) {
        Instant now = Instant.now();
        apply(
                new TutorRegistered(
                        command.getTutorId(),
                        command.getFirstName(),
                        command.getLastName(),
                        command.getDni(),
                        now
                )
        );
    }

    @CommandHandler
    public void handle(EditTutor command) {
        Instant now = Instant.now();
        apply(
                new TutorEdited(
                        command.getTutorId(),
                        command.getFirstName(),
                        command.getLastName(),
                        command.getDni(),
                        now
                )
        );
    }

    @EventSourcingHandler
    protected void on(TutorRegistered event) {
        tutorId = event.getTutorId();
        firstName = event.getFirstName();
        lastName = event.getLastName();
        dni = event.getDni();
        status = TutorStatus.ACTIVE;
    }

    @EventSourcingHandler
    protected void on(TutorEdited event) {
        firstName = event.getFirstName();
        lastName = event.getLastName();
        dni = event.getDni();
    }
}
