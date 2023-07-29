package com.example.demo.Course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseClassRepo extends JpaRepository<CourseClass,Integer> {
    Optional<CourseClass> findById(Optional<CourseClass> byId);

}
