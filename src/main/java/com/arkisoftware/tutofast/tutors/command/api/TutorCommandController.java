package com.arkisoftware.tutofast.tutors.command.api;

import com.arkisoftware.tutofast.tutors.api.ApiController;
import com.arkisoftware.tutofast.tutors.application.Notification;
import com.arkisoftware.tutofast.tutors.application.Result;
import com.arkisoftware.tutofast.tutors.command.application.dtos.request.EditTutorRequest;
import com.arkisoftware.tutofast.tutors.command.application.dtos.request.RegisterTutorRequest;
import com.arkisoftware.tutofast.tutors.command.application.dtos.response.EditTutorResponse;
import com.arkisoftware.tutofast.tutors.command.application.dtos.response.RegisterTutorResponse;
import com.arkisoftware.tutofast.tutors.command.application.services.TutorApplicationService;
import com.arkisoftware.tutofast.tutors.command.infra.TutorDniRepository;
import io.swagger.annotations.Api;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutors")
@Api(tags = "Tutors")
public class TutorCommandController {
    private final TutorApplicationService tutorApplicationService;
    private final CommandGateway commandGateway;
    private final TutorDniRepository tutorDniRepository;

    public TutorCommandController(TutorApplicationService tutorApplicationService, CommandGateway commandGateway, TutorDniRepository tutorDniRepository) {
        this.tutorApplicationService = tutorApplicationService;
        this.commandGateway = commandGateway;
        this.tutorDniRepository = tutorDniRepository;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterTutorRequest registerTutorRequest) {
        try {
            Result<RegisterTutorResponse, Notification> result = tutorApplicationService.register(registerTutorRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @PutMapping("/{tutorId}")
    public ResponseEntity<Object> edit(@PathVariable("tutorId") String tutorId, @RequestBody EditTutorRequest editTutorRequest) {
        try {
            editTutorRequest.setTutorId(tutorId);
            Result<EditTutorResponse, Notification> result = tutorApplicationService.edit(editTutorRequest);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (AggregateNotFoundException exception) {
            return ApiController.notFound();
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }
}
