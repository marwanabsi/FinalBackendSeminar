package com.example.demo.user;

import com.example.demo.Course.*;


import com.example.demo.Course.Services.TeacherAssistantRequest;
import com.example.demo.Scheduling.evaluation.Evaluation;
import com.example.demo.Scheduling.evaluation.EvaluationRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User getUserInfoById(String Id) {
        User user = userRepository.findByEmail(Id)
                .orElseThrow(() -> new EntityNotFoundException("TeacherAssistant not found with ID: " + Id));

        return user;
    }





}
