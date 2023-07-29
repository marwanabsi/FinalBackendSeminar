package com.example.demo.Course.Services;

import com.example.demo.Course.*;
import com.example.demo.Scheduling.evaluation.Evaluation;
import com.example.demo.Scheduling.evaluation.EvaluationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseClassService {

    private final CourseClassRepo courseClassRepo;
    private final CourseRepo courseRepo;

    public ResponseEntity AddCourseClass(CourseClassRequest request) {
        var course = courseRepo.findByName(request.getCourse());
        var courseDays = new CourseDays();
        courseDays.setEndTime(request.getEndTime());
        var courseDayss = new CourseDays();
        courseDayss.setStartTime(request.getStartTime());

        var courseClass = CourseClass.builder()
                .course(course)
                .daysAndTimes(Collections.singletonList(courseDays))
                .daysAndTimes(Collections.singletonList(courseDayss))
                .build();

        courseClassRepo.save(courseClass);
        System.out.println(courseClassRepo.findById(5));
        return ResponseEntity.ok("Done");
    }
}

