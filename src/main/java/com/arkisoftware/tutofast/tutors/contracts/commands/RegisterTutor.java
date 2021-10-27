package com.arkisoftware.tutofast.tutors.contracts.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class RegisterTutor {
    @TargetAggregateIdentifier
    String tutorId;
    String firstName;
    String lastName;
    String dni;
}
