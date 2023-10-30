package net.examsection.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import net.examsection.springboot.model.History;
import net.examsection.springboot.model.Teacher;
import net.examsection.springboot.model.Teacher1;
import net.examsection.springboot.model.Teachers;
import net.examsection.springboot.repository.Teacher1Repository;
import net.examsection.springboot.repository.TeacherRepository;
import net.examsection.springboot.repository.TeachersRepository;
import net.examsection.springboot.service.TeacherService;

@RestController
@RequestMapping("/api/absent-students")
@CrossOrigin(origins = "http://localhost:4200")
public class Teachercontroller {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private final TeachersRepository teachersRepository;

    @Autowired
    private Teacher1Repository teacher1Repository;

    public Teachercontroller(TeachersRepository teachersRepository) {
        this.teachersRepository = teachersRepository;
    }

//
//    @PostMapping("/save")
//    public ResponseEntity<String> saveAbsentStudents(@RequestBody Map<String, List<String>> data) {
//        List<String> teacherNames = data.get("teacherNames");
//        List<String> blockNumbers = data.get("blockNumbers");
//
//        if (teacherNames == null || blockNumbers == null) {
//            return new ResponseEntity<>("Invalid request data", HttpStatus.BAD_REQUEST);
//        }
//
//        if (teacherNames.size() != blockNumbers.size()) {
//            return new ResponseEntity<>("Mismatched data", HttpStatus.BAD_REQUEST);
//        }
//
//        // Process the data and save it to the database
//        for (int i = 0; i < teacherNames.size(); i++) {
//            Teacher teacher = new Teacher();
//            teacher.setTeacher_name(teacherNames.get(i));
//            teacher.setBlock_no(Integer.parseInt(blockNumbers.get(i)));
//            teacherrepo.save(teacher);
//        }
//
//        return new ResponseEntity<>("Data saved successfully", HttpStatus.CREATED);
//    }



    @GetMapping("/fetch1")
    public List<Teacher1> fetch1(){
        return teacherService.fetchrecords1();
    }

    @GetMapping("/fetch")
    public List<Teacher> fetch(){
        return teacherService.fetchrecords();
    }

    @GetMapping("/all1")
    public List<Teachers> getAllAbsentStudents() {
        return teachersRepository.findAll();
    }
 

    @GetMapping("/fetch12")
    public List<History>fetch3(){
        return teacherService.fetchrecords12();
    }



    @GetMapping("/block-no-values")
    public List<Integer> getDistinctBlockNoValues() {
        List<Integer> distinctBlockNos = this.teacher1Repository.findDistinctBlockNo();
        return distinctBlockNos;
    }




    @PostMapping("/save10")
    public void saveTeacher1(@RequestBody Teachers teacher) {
        teacherService.saveTeachers(teacher);
    }


    @DeleteMapping("/delete/{teacherName}")
    public String deleteTeacherByName(@PathVariable String teacherName) {
        try {
            teacherService.deleteTeacherByName(teacherName);
            return "Teacher with name " + teacherName + " deleted successfully.";
        } catch (Exception e) {
            return "Error deleting teacher: " + e.getMessage();
        }
    }


    @PostMapping("/save2")
    public ResponseEntity<String> saveHistoryData(@RequestBody List<History> historyData) {
        try {
            List<History> savedData = teacherService.saveHistoryData(historyData);
            return ResponseEntity.ok("Data saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving data: " + e.getMessage());
        }
    }



}
