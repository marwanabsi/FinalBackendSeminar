package com.example.demo.Course.Services;

import com.example.demo.Course.*;


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
public class TeacherAssistantService {

    private final TeacherAssistantRepo teacherAssistantRepo;
    private final EvaluationRepo evaluationRepo;
    private final CourseRepo courseRepo;
    private final TeacherLoadReop teacherLoadReop;

    public ResponseEntity RegisterTeacher(TeacherAssistantRequest request) {
        var  teacherAssistant = TeacherAssistant.builder()
                .name(request.getName())
                .isAvailable(request.isAvailable())
                .isFulltime(request.isFulltime())
                .build();
        teacherAssistantRepo.save(teacherAssistant);
        var evaluation = Evaluation.builder().
                isInRamallah(request.isInRamallah()).
                experienceClassesNumber(request.getExperienceClassesNumber()).
                isPartTime(request.isPartTime()).
                isScholarship(request.isScholarship()).
                ExperienceYears(request.getExperienceYears()).VacationDay(request.getVacationDay()).
                isFullTimeScholarShip(request.isFullTimeScholarShip()).
                isPartTimeScholarShip(request.isPartTimeScholarShip()).
                preferedLabsLoad(request.getPreferedLoad()).teacherAssistant(teacherAssistant).build();

        evaluation.setTeacherAssistant(teacherAssistant);

        Course course = new Course();
        List<Course> courseList = new ArrayList<Course>();
        for(int i = 0; i < request.getCourseList().size();i++){
            course =  courseRepo.findByName(request.getCourseList().get(i));
            courseList.add(course);
        }
        evaluation.setCourseList(courseList);

        evaluationRepo.save(evaluation);
        teacherAssistant.setEvaluation(evaluation);
        teacherAssistantRepo.save(teacherAssistant);
        return ResponseEntity.ok( "Done" ) ;
    }

    public List<TeacherLoadDTO> getAllTeacherLoadsByAssistantId(int assistantId) {
        TeacherAssistant teacherAssistant = teacherAssistantRepo.findById(assistantId).orElse(null);
        List<TeacherLoad> teacherLoadList = teacherLoadReop.getteacherload(teacherAssistant);

        List<TeacherLoadDTO> teacherLoadDTOList = new ArrayList<>();

        for (TeacherLoad teacherLoad : teacherLoadList) {
            TeacherLoadDTO teacherLoadDTO = new TeacherLoadDTO();
            teacherLoadDTO.setId(teacherLoad.getId());
            teacherLoadDTO.setDay(teacherLoad.getDay());
            teacherLoadDTO.setStartTime(teacherLoad.getStartTime());
            teacherLoadDTO.setEndTime(teacherLoad.getEndTime());
            teacherLoadDTO.setCourseName(teacherLoad.getCourse().getCourse().getName());
            // Set other fields if needed

            teacherLoadDTOList.add(teacherLoadDTO);
        }

        return teacherLoadDTOList;
    }



    public TeacherAssistant getTeacherAssInfo(int assistantId) {
        TeacherAssistant teacherAssistant = teacherAssistantRepo.findById(assistantId)
                .orElseThrow(() -> new EntityNotFoundException("TeacherAssistant not found with ID: " + assistantId));

        return teacherAssistant;
    }

}
