package com.arkisoftware.tutofast.tutors.command.application.validators;

import com.arkisoftware.tutofast.tutors.command.infra.TutorDniRepository;
import com.arkisoftware.tutofast.tutors.command.domain.Tutor;
import org.springframework.stereotype.Component;
import org.axonframework.modelling.command.Repository;

@Component
public class EditTutorValidator {
    private final TutorDniRepository tutorDniRepository;
    private final Repository<Tutor> tutorRepository;

    public EditTutorValidator(TutorDniRepository tutorDniRepository, Repository<Tutor> tutorRepository) {
        this.tutorDniRepository = tutorDniRepository;
        this.tutorRepository = tutorRepository;
    }

}
