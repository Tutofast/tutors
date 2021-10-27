package com.arkisoftware.tutofast.tutors.query.api;

import com.arkisoftware.tutofast.tutors.query.projections.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get tutor by id", response = TutorView.class)
    public ResponseEntity<TutorView> getById(@PathVariable("id") String id) {
        try {
            Optional<TutorView> tutorViewOptional = tutorViewRepository.findById(id);
            if (tutorViewOptional.isPresent()) {
                return new ResponseEntity<TutorView>(tutorViewOptional.get(), HttpStatus.OK);
            }
            return new ResponseEntity("NOT FOUND", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/dni/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get tutor", response = TutorView.class)
    public ResponseEntity<TutorView> getByDocument(@PathVariable("dni") String dni) {
        try {
            Optional<TutorView> tutorViewOptional = tutorViewRepository.getByDni(dni);
            if (tutorViewOptional.isPresent()) {
                return new ResponseEntity<TutorView>(tutorViewOptional.get(), HttpStatus.OK);
            }
            return new ResponseEntity("NOT_FOUND", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/history/{id}")
    @ApiOperation(value = "Get tutor history", response = List.class)
    public ResponseEntity<List<TutorHistoryView>> getHistoryById(@PathVariable("id") String id){
        System.out.println(id);
        try {
            List<TutorHistoryView> tutors = tutorHistoryViewRepository.getHistoryByTutorId(id);
            return new ResponseEntity<List<TutorHistoryView>>(tutors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
