package com.arkisoftware.tutofast.tutors.query.api;

import com.arkisoftware.tutofast.tutors.query.projections.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tutors")
@Api(tags = "Tutors")
public class TutorQueryController {
    private final TutorViewRepository tutorViewRepository;
    private final TutorHistoryViewRepository tutorHistoryViewRepository;

    public TutorQueryController(TutorViewRepository tutorViewRepository, TutorHistoryViewRepository tutorHistoryViewRepository) {
        this.tutorViewRepository = tutorViewRepository;
        this.tutorHistoryViewRepository = tutorHistoryViewRepository;
    }

    @GetMapping("")
    @ApiOperation(value="Get all Tutors", response = List.class)
    public ResponseEntity<List<TutorView>> getAll() {
        try {
            return new ResponseEntity<List<TutorView>>(tutorViewRepository.findAll(), HttpStatus.OK);
        } catch ( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
