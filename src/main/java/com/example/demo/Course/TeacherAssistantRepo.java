package com.example.demo.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherAssistantRepo extends JpaRepository<TeacherAssistant,Integer> {

}
