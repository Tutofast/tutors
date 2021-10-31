package com.arkisoftware.tutofast.tutors.command.application.services;

import com.arkisoftware.tutofast.tutors.command.application.dtos.response.EditTutorResponse;
import com.arkisoftware.tutofast.tutors.command.application.dtos.request.EditTutorRequest;
import com.arkisoftware.tutofast.tutors.command.application.validators.EditTutorValidator;

import com.arkisoftware.tutofast.tutors.command.application.dtos.response.RegisterTutorResponse;
import com.arkisoftware.tutofast.tutors.command.application.dtos.request.RegisterTutorRequest;
import com.arkisoftware.tutofast.tutors.command.application.validators.RegisterTutorValidator;

import com.arkisoftware.tutofast.tutors.contracts.commands.RegisterTutor;
import com.arkisoftware.tutofast.tutors.contracts.commands.EditTutor;

import org.arkisoftware.tutofast.common.application.Notification;
import org.arkisoftware.tutofast.common.application.Result;
import org.arkisoftware.tutofast.common.application.ResultType;

import com.arkisoftware.tutofast.tutors.command.infra.TutorDniRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class TutorApplicationService {
    private final RegisterTutorValidator registerTutorValidator;
    private final EditTutorValidator editTutorValidator;
    private final CommandGateway commandGateway;
    private final TutorDniRepository tutorDniRepository;

    public TutorApplicationService(RegisterTutorValidator registerTutorValidator, EditTutorValidator editTutorValidator, CommandGateway commandGateway, TutorDniRepository tutorDniRepository) {
        this.registerTutorValidator = registerTutorValidator;
        this.editTutorValidator = editTutorValidator;
        this.commandGateway = commandGateway;
        this.tutorDniRepository = tutorDniRepository;
    }

    public Result<RegisterTutorResponse, Notification> register(RegisterTutorRequest registerTutorRequest) throws Exception {
        Notification notification = this.registerTutorValidator.validate(registerTutorRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String tutorId = UUID.randomUUID().toString();
        RegisterTutor registerTutor = new RegisterTutor(
                tutorId,
                registerTutorRequest.getFirstName().trim(),
                registerTutorRequest.getLastName().trim(),
                registerTutorRequest.getDni().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(registerTutor);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterTutorResponse registerTutorResponseDto = new RegisterTutorResponse(
                registerTutor.getTutorId(),
                registerTutor.getFirstName(),
                registerTutor.getLastName(),
                registerTutor.getDni()
        );
        return Result.success(registerTutorResponseDto);
    }

    public Result<EditTutorResponse, Notification> edit(EditTutorRequest editTutorRequest) throws Exception {
        Notification notification = this.editTutorValidator.validate(editTutorRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        EditTutor editTutor = new EditTutor(
                editTutorRequest.getTutorId().trim(),
                editTutorRequest.getFirstName().trim(),
                editTutorRequest.getLastName().trim(),
                editTutorRequest.getDni().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(editTutor);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        EditTutorResponse editTutorResponse = new EditTutorResponse(
                editTutor.getTutorId(),
                editTutor.getFirstName(),
                editTutor.getLastName(),
                editTutor.getDni()
        );
        return Result.success(editTutorResponse);
    }
}
