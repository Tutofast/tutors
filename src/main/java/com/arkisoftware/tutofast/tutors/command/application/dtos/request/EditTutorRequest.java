package com.arkisoftware.tutofast.tutors.command.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

public class EditTutorRequest {
    private @Setter @Getter String tutorId;
    private @Getter String firstName;
    private @Getter String lastName;
    private @Getter String dni;
}
