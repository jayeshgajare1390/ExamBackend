package net.examsection.springboot.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.examsection.springboot.model.AbsentStudent;
import net.examsection.springboot.repository.AbsentStudentRepository;

import java.util.List;
import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/absent-students")
@CrossOrigin(origins = "http://localhost:4200") // Adjust the origin URL as needed
public class AbsentStudentController {
    private final AbsentStudentRepository absentStudentRepository;

    @Autowired
    public AbsentStudentController(AbsentStudentRepository absentStudentRepository) {
        this.absentStudentRepository = absentStudentRepository;
    }
    @PostMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteAbsentStudent(@RequestBody Map<String, Object> params) {
        int prn = (int) params.get("prn");
        int blockNumber = (int) params.get("blockNumber");
        String date = (String) params.get("date");

        absentStudentRepository.deleteByPrnAndBlockNoAndDate(prn, blockNumber, date);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Record(s) deleted successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }




    @PostMapping("/save")
    public ResponseEntity<String> saveAbsentStudents(@RequestBody List<AbsentStudent> absentStudents) {
    	for (AbsentStudent student : absentStudents) {
            absentStudentRepository.save(student);
        }

        return new ResponseEntity<>("Data saved successfully", HttpStatus.CREATED);
    }
    @GetMapping("/{classId}")
    public Map<String, List<AbsentStudent>> getAbsentStudentsByClass(@PathVariable String classId) {
        List<AbsentStudent> absentStudents = absentStudentRepository.findByyear(classId);

        Map<String, List<AbsentStudent>> groupedData = absentStudents.stream()
                .collect(Collectors.groupingBy(AbsentStudent::getProgramname));

        return groupedData;
    }
//    @GetMapping("/exists")
//    public ResponseEntity<String> checkIfExists(@RequestParam String date, @RequestParam Integer blockNo) {
//        boolean exists = absentStudentRepository.existsByDateAndBlockNo(date, blockNo);
//        String message = exists ? "Record exists" : "Record does not exist";
//        return ResponseEntity.ok(message);
//    }
    @GetMapping("/report/generated")
    public ResponseEntity<Boolean> isReportGenerated(
            @RequestParam Integer blockNumber,
            @RequestParam String date) {
        List<AbsentStudent> existingData = absentStudentRepository.findAbsentStudentsByBlockNoAndDate(blockNumber, date);
        boolean reportGenerated = !existingData.isEmpty();
        return new ResponseEntity<>(reportGenerated, HttpStatus.OK);
    }

    @GetMapping("/report/absent")
    public List<AbsentStudent> findAbsentStudents(
            @RequestParam String examYear,
            @RequestParam String year,
            @RequestParam String examination) {
        return absentStudentRepository.findCustomAbsentStudents(examYear, year, examination);
    }

    @GetMapping("/all")
    public List<AbsentStudent> getAllAbsentStudents() {
        return absentStudentRepository.findAll();
    }
}
