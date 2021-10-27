package com.arkisoftware.tutofast.tutors.contracts.events;

import lombok.Value;

import java.time.Instant;

@Value
public class TutorEdited {
    String tutorId;
    String firstName;
    String lastName;
    String dni;
    Instant occurredOn;
}
