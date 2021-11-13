package com.arkisoftware.tutofast.tutors.command.application.validators;

import com.arkisoftware.tutofast.tutors.command.application.dtos.request.RegisterTutorRequest;
import com.arkisoftware.tutofast.tutors.command.infra.TutorDni;
import com.arkisoftware.tutofast.tutors.command.infra.TutorDniRepository;
import org.springframework.stereotype.Component;
import com.arkisoftware.tutofast.tutors.common.application.Notification;

import java.util.Optional;

@Component
public class RegisterTutorValidator {
    private final TutorDniRepository tutorDniRepository;

    public RegisterTutorValidator(TutorDniRepository tutorDniRepository) {
        this.tutorDniRepository = tutorDniRepository;
    }

    public Notification validate(RegisterTutorRequest registerTutorRequest) {
        Notification notification = new Notification();
        String firstName = registerTutorRequest.getFirstName().trim();
        String lastName = registerTutorRequest.getLastName().trim();
        String dni = registerTutorRequest.getDni().trim();

        if (firstName.isEmpty()) {
            notification.addError("Tutor firstname is required");
        }
        if (lastName.isEmpty()) {
            notification.addError("Tutor lastname is required");
        }
        if (dni.isEmpty()) {
            notification.addError("Tutor DNI is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        Optional<TutorDni> tutorDniOptional = tutorDniRepository.findById(dni);
        if (tutorDniOptional.isPresent()) {
            notification.addError("Tutor DNI is taken");
        }
        return notification;
    }


}
