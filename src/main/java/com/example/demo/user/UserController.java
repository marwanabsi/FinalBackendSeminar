package com.example.demo.user;


import com.example.demo.Course.Services.TeacherAssistantRequest;
import com.example.demo.Course.Services.TeacherAssistantService;
import com.example.demo.Course.TeacherAssistant;
import com.example.demo.Course.TeacherLoad;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    @GetMapping("/{id}")
    public ResponseEntity<User> getTeacherAssInfo(@PathVariable String id) {
        User user = service.getUserInfoById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}