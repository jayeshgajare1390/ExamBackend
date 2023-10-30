package net.examsection.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import net.examsection.springboot.model.History;
import net.examsection.springboot.model.Teacher;
import net.examsection.springboot.model.Teacher1;
import net.examsection.springboot.model.Teachers;
import net.examsection.springboot.repository.HistoryRepository;
import net.examsection.springboot.repository.Teacher1Repository;
import net.examsection.springboot.repository.TeacherRepository;
import net.examsection.springboot.repository.TeachersRepository;


@Service
public class TeacherService {

    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private Teacher1Repository teacher1Repository;

    @Autowired
private final TeachersRepository teachersRepository;

    public TeacherService(TeachersRepository teachersRepository) {
        this.teachersRepository = teachersRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public void saveTeacher(String teacherName) {

    }

    public List<Teacher> fetchrecords() {
        return teacherRepository.findAll();
    }

    public List<Teacher1> fetchrecords1(){
        return teacher1Repository.findAll();
    }

  

    private List<Teachers> teachers = new ArrayList<>();

    public void saveTeachers(Teachers teacher) {

        teachersRepository.save(teacher);
    }

//    public void deleteTeacherByName(String teacherName) {
//        teachersRepository.deleteByTeacherName(teacherName);
//    }


    public void deleteTeacherByName(String teacherName) {
        teachersRepository.deleteByTeacherName(teacherName);
    }

    public List<History> saveHistoryData(List<History> historyData) {
        return historyRepository.saveAll(historyData);
    }

    public List<History> fetchrecords12(){
        return historyRepository.findAll();
    }


}
