package com.example.sampleproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "request_event")
public class RequestEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, optional = false)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "event_name")
    private String eventName;

    public RequestEvent(){ }

    public RequestEvent(Teacher teacher, Student student){
        this.teacher = teacher;
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestEvent)) return false;
        RequestEvent that = (RequestEvent) o;
        return Objects.equals(teacher.getEmailId(), that.teacher.getEmailId()) &&
                Objects.equals(student.getEmailId(), that.student.getEmailId()) &&
                Objects.equals(eventName, that.eventName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacher.getEmailId(), student.getEmailId(), eventName);
    }
}
