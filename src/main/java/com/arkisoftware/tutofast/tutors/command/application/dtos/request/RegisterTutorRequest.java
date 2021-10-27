package com.arkisoftware.tutofast.tutors.command.application.dtos.request;

import lombok.Value;

@Value
public class RegisterTutorRequest {
    String firstName;
    String lastName;
    String dni;
}
