package com.arkisoftware.tutofast.tutors.command.application.validators;

import com.arkisoftware.tutofast.tutors.command.application.dtos.request.EditTutorRequest;
import com.arkisoftware.tutofast.tutors.command.infra.TutorDni;
import com.arkisoftware.tutofast.tutors.command.infra.TutorDniRepository;
import com.arkisoftware.tutofast.tutors.command.domain.Tutor;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.stereotype.Component;
import org.axonframework.modelling.command.Repository;
import org.arkisoftware.tutofast.common.application.Notification;

import java.util.Optional;

@Component
public class EditTutorValidator {
    private final TutorDniRepository tutorDniRepository;
    private final Repository<Tutor> tutorRepository;

    public EditTutorValidator(TutorDniRepository tutorDniRepository, Repository<Tutor> tutorRepository) {
        this.tutorDniRepository = tutorDniRepository;
        this.tutorRepository = tutorRepository;
    }

    public Notification validate(EditTutorRequest editTutorRequest) {
        Notification notification = new Notification();
        String tutorId = editTutorRequest.getTutorId().trim();
        String firstName = editTutorRequest.getFirstName().trim();
        String lastName = editTutorRequest.getLastName().trim();
        String dni = editTutorRequest.getDni().trim();
        if (tutorId.isEmpty()) {
            notification.addError("Tutor ID is required");
        }
        loadCustomAggregate(tutorId);
        if (firstName.isEmpty()) {
            notification.addError("Tutor firstName is required");
        }
        if (lastName.isEmpty()) {
            notification.addError("Tutor lastName is required");
        }
        if (dni.isEmpty()) {
            notification.addError("Tutor DNI is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        Optional<TutorDni> tutorDni = tutorDniRepository.getByDniForDistinctTutorId(dni, tutorId);
        if (tutorDni.isPresent()) {
            notification.addError("Tutor DNI is taken");
        }
        return notification;
    }

    private void loadCustomAggregate(String tutorId) {
        UnitOfWork unitOfWork = null;
        try {
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            tutorRepository.load(tutorId);
            unitOfWork.commit();
        } catch (AggregateNotFoundException ex) {
            unitOfWork.commit();
            throw ex;
        } catch (Exception ex) {
            if (unitOfWork != null) unitOfWork.rollback();
        }
    }
}
