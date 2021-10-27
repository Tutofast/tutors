package com.arkisoftware.tutofast.tutors.command.infra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TutorDni {
    @Id
    @Column(length = 8)
    public String dni;
    String tutorId;

    public TutorDni() {}

    public TutorDni(String dni, String tutorId) {
        this.dni = dni;
        this.tutorId = tutorId;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }
}
