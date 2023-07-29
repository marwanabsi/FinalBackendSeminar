package com.example.demo.home;


import com.example.demo.Course.CourseClass;
import com.example.demo.Course.Services.TeacherAssistantService;
import com.example.demo.Course.TeacherAssistant;
import com.example.demo.Scheduling.Edge;
import com.example.demo.Scheduling.GraphData;
import com.example.demo.Scheduling.TeacherAssistantScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HomeController {

    private final TeacherAssistantScheduler teacherAssistantScheduler;

    @GetMapping("/schedule")
    public ResponseEntity<TeacherAssistantScheduler> schedule(

    ){

        teacherAssistantScheduler.schedule();
        List<GraphData> graphDataList = teacherAssistantScheduler.getData();
        for(int i = 0; i < graphDataList.size();i++){
            if( graphDataList.get(i).getType().equals("CourseClass")){
                Optional<CourseClass> k = teacherAssistantScheduler.getCourseClassRepo().findById(graphDataList.get(i).getId());
                System.out.println( "Class id = " + k.get().getId() +" Class Course =" +  k.get().getCourse().getName());



            }
            else {
                Optional<TeacherAssistant> q = teacherAssistantScheduler.getTeacherAssistantRepo().findById(graphDataList.get(i).getId());
                System.out.println( "Teacher id = " + q.get().getId() +" Teacher Name =" +  q.get().getName());

                List<Edge<GraphData>> edgeList = teacherAssistantScheduler.getEdges(graphDataList.get(i));
                if(edgeList != null){
                    System.out.println(edgeList.size());
                }
                for(int z = 0; z < edgeList.size(); z++){
                    System.out.println("from " + edgeList.get(z).getSource().getType() +" id =  " + edgeList.get(z).getSource().getId()+" to :"+ edgeList.get(z).getDestination().getType() + " weight = " +  edgeList.get(z).getWeight());
                }
            }
            System.out.println();
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
