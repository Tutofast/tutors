package com.arkisoftware.tutofast.tutors.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class TutorHistoryView {
    @Id @GeneratedValue @Getter @Setter
    private Long tutorHistoryId;
    @Column(length = 36) @Getter @Setter
    private String tutorId;
    @Column(length = 50) @Getter @Setter
    private String firstName;
    @Column(length = 50) @Getter @Setter
    private String lastName;
    @Column(length = 8) @Getter @Setter
    private String dni;
    @Column(length = 20) @Getter @Setter
    private String status;
    @Getter @Setter
    private Instant createdAt;

    public TutorHistoryView () {}

    public TutorHistoryView(String tutorId, String firstName, String lastName, String dni, String status, Instant createdAt) {
        this.tutorId = tutorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.status = status;
        this.createdAt = createdAt;
    }

    public TutorHistoryView(TutorHistoryView tutorHistoryView) {
        this.tutorId = tutorHistoryView.getTutorId();
        this.firstName = tutorHistoryView.getFirstName();
        this.lastName = tutorHistoryView.getLastName();
        this.dni = tutorHistoryView.getDni();
        this.status = tutorHistoryView.getStatus();
        this.createdAt = tutorHistoryView.getCreatedAt();
    }

}
