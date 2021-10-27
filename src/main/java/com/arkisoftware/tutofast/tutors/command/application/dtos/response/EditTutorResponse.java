package com.arkisoftware.tutofast.tutors.command.application.dtos.response;

import lombok.Value;

@Value
public class EditTutorResponse {
    String tutorId;
    String firstName;
    String lastName;
    String dni;
}
