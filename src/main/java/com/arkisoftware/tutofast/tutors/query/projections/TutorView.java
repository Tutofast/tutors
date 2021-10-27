package com.arkisoftware.tutofast.tutors.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.Instant;

@Entity
public class TutorView {
    @Id @Column(length = 36) @Getter @Setter
    private String tutorId;
    @Column(length = 50) @Getter @Setter
    private String firstName;
    @Column(length = 50) @Getter @Setter
    private String lastName;
    @Column(length = 8, unique = true) @Getter @Setter
    private String dni;
    @Column(length = 20) @Getter @Setter
    private String status;
    private Instant createdAt;
    @Column(nullable = true) @Getter @Setter
    private Instant updatedAt;

    public TutorView(){}

    public TutorView(String tutorId, String firstName, String lastName, String dni, String status, Instant createdAt) {
        this.tutorId = tutorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.status = status;
        this.createdAt = createdAt;
    }
}
