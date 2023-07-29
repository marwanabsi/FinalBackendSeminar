package com.example.demo.Course;

import com.example.demo.Scheduling.evaluation.Evaluation;
import com.example.demo.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Entity
@Data  //makes getters and setters by default
@Builder //
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TeacherAssistant")
public class TeacherAssistant {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double evauluationScore;
    private boolean isAvailable;
    private boolean isFulltime;
    private int loadHours;

    @OneToMany(
            mappedBy = "teacherAssistant",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TeacherLoad> teacherLoads ;

   // @OneToMany(mappedBy="TeacherAssistant")
    //private Set<CourseClass> courseClasses;
   @OneToMany(
           mappedBy = "teacherAssistant",
           cascade = CascadeType.ALL,
           orphanRemoval = true
   )
   private List<CourseClass> classes = new ArrayList<>();

    public void AddClass(CourseClass classes){
        this.classes.add(classes);
    }
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_evlauation")
    private Evaluation evaluation;



    public int makeEvaluation(List<Course> courseList, boolean isInRamallah, int experienceClassesNumber, boolean isScholarship, int preferedLabsLoad, boolean isPartTimeScholarShip, boolean isFullTimeScholarShip, boolean isPartTime, String vacationDay, int experienceYears){
        Evaluation evaluation1 = new Evaluation();
        evaluation1.setTeacherAssistant(this);
        evaluation1.setCourseList(courseList);
        evaluation1.setInRamallah(isInRamallah);
        evaluation1.setExperienceYears(experienceYears);
        evaluation1.setVacationDay(vacationDay);
        evaluation1.setPartTime(isPartTime);
        evaluation1.setFullTimeScholarShip(isFullTimeScholarShip);
        evaluation1.setPartTimeScholarShip(isPartTimeScholarShip);
        evaluation1.setPreferedLabsLoad(preferedLabsLoad);
        evaluation1.setScholarship(isScholarship);
        evaluation1.setExperienceClassesNumber(experienceClassesNumber);
      evaluation1.evaluate();
      return evaluation1.getEvaluationScore();
    }

    public void addClass(CourseClass bestcourse) {
        if (hasTime(bestcourse)) {
            for (int i =0; i < bestcourse.getDaysAndTimes().size();i++){
                TeacherLoad teacherLoad = new TeacherLoad();
                teacherLoad.setStartTime(bestcourse.getDaysAndTimes().get(i).getStartTime());
                teacherLoad.setEndTime(bestcourse.getDaysAndTimes().get(i).getEndTime());
                teacherLoad.setDay(bestcourse.getDaysAndTimes().get(i).getDay());
                teacherLoad.setCourse(bestcourse);
                teacherLoad.setTeacherAssistant(this);
                this.teacherLoads.add(teacherLoad);
            }
        }
    }

    public boolean hasTime(CourseClass course){
        for (int i=0 ; i <teacherLoads.size();i++){
            for (int j=0 ; j < course.getDaysAndTimes().size();j++){

            if(teacherLoads.get(i).getStartTime().equals(course.getDaysAndTimes().get(j).getStartTime())
                    && teacherLoads.get(i).getDay().equals(course.getDaysAndTimes().get(j).getDay()) ){
                return false;
            }
        }
        }
        return true;
    }
}
