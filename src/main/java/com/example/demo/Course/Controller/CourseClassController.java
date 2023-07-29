package com.example.demo.Course.Controller;

import com.example.demo.Course.Services.CourseClassRequest;
import com.example.demo.Course.Services.CourseClassService;
import com.example.demo.Course.Services.TeacherAssistantRequest;
import com.example.demo.Course.Services.TeacherAssistantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courseclass")
@RequiredArgsConstructor
public class CourseClassController {
    private final CourseClassService service;
    @PostMapping("/addClass")
    public ResponseEntity<TeacherAssistantService> RegisterCourseClass(
            @RequestBody CourseClassRequest request

    ){
        return service.AddCourseClass(request);
    }


}
