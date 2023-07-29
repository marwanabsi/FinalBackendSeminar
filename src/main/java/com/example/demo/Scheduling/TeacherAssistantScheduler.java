package com.example.demo.Scheduling;

import com.example.demo.Course.*;
import com.example.demo.Scheduling.evaluation.CourseTeacherEvaluation;
import com.example.demo.Scheduling.evaluation.EvaluationRepo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.List;
@Data
@Service
@RequiredArgsConstructor
public class TeacherAssistantScheduler {

    private final CourseRepo courseRepo;
    private final CourseClassRepo courseClassRepo;
    private final EvaluationRepo evaluationRepo;
    private final TeacherAssistantRepo teacherAssistantRepo;
    private final InstructorRepo instructorRepo;
    private Graph<GraphData> graph = new Graph<GraphData>();;
    private List<GraphData> data;
    private List<GraphData> vertices;


    public void schedule(){
        double max = 0;
        int bestcourseindex=0;
        List<CourseClass> courseClassList = courseClassRepo.findAll();
        for(int i = 0;i < courseClassList.size();i++){
            if(courseClassList.get(i).HasTA()) {
                graph.addVertex(new GraphData(courseClassList.get(i).getId(), "CourseClass"));
            }
        }
        List<TeacherAssistant> teacherAssistants = teacherAssistantRepo.findAll();

         data =graph.getVertices().stream().toList();
        for(int i = 0;i < teacherAssistants.size();i++){

            GraphData thisVertex = new GraphData(teacherAssistants.get(i).getId(),"TeacherAssistant");
            graph.addVertex(new GraphData(teacherAssistants.get(i).getId(),"TeacherAssistant"));
            for(int j = 0;j < data.size();j++){
                if(data.get(j).getType().equals("CourseClass")){


                CourseTeacherEvaluation courseeval = new CourseTeacherEvaluation();
                double eval=courseeval.getEvaluation(courseClassRepo.findById(data.get(j).getId()),teacherAssistants.get(i));
               graph.addEdge(thisVertex,data.get(j),eval+teacherAssistants.get(i).getEvauluationScore());

               if(eval+teacherAssistants.get(i).getEvauluationScore()  > max ){
                   max = eval+teacherAssistants.get(i).getEvauluationScore();
                   bestcourseindex = j;
               }

                }

            }
             //   teacherAssistants.get(i).addClass(courseClassRepo.findById(data.get(bestcourseindex).getId()).get());
         //   System.out.println(teacherAssistants.get(i).getName() + " ");
        }


    }

    public void sort(){
        data =graph.getVertices().stream().toList();




    }



    public List<Edge<GraphData>> getEdges(GraphData vertix){
        return graph.getEdges(vertix);
    }

}
