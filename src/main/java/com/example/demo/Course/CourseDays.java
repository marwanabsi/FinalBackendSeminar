package com.example.demo.Course;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class CourseDays {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private int id;
    private String Day;
    private String startTime;
    private String endTime;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseClass course;

}
