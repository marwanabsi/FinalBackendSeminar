package com.example.demo.Course.Controller;

import com.example.demo.Course.Services.TeacherAssistantRequest;
import com.example.demo.Course.Services.TeacherAssistantService;
import com.example.demo.Scheduling.TeacherAssistantScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Schedule")
@RequiredArgsConstructor
public class ScheduleController {
    public ResponseEntity<String> Home(){

        return ResponseEntity.ok( "hello " ) ;
    }



}
