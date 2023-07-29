package com.example.demo.Course.Controller;

import com.example.demo.Course.Services.TeacherAssistantRequest;
import com.example.demo.Course.Services.TeacherAssistantService;
import com.example.demo.Course.TeacherAssistant;
import com.example.demo.Course.TeacherLoad;
import com.example.demo.Course.TeacherLoadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/teacherassistant")
@RequiredArgsConstructor
public class TeacherAssistantController {
    private final TeacherAssistantService service;
    @PostMapping("/registerteacher")
    public ResponseEntity<TeacherAssistantService> RegisterTeacher(
            @RequestBody TeacherAssistantRequest request

    ){
        return service.RegisterTeacher(request);
    }

    @GetMapping("/{id}/teacherloads")
    public ResponseEntity<List<TeacherLoadDTO>> getAllTeacherLoadsByAssistantId(@PathVariable int id) {
        List<TeacherLoadDTO> teacherLoads = service.getAllTeacherLoadsByAssistantId(id);
        return new ResponseEntity<>(teacherLoads, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeacherAssistant> getTeacherAssInfo(@PathVariable int id) {
        TeacherAssistant teacherLoads = service.getTeacherAssInfo(id);
        return new ResponseEntity<>(teacherLoads, HttpStatus.OK);
    }


}
