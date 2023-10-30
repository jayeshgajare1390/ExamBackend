package net.examsection.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer teacher_id;


    private String teacher_name;

    private String techer_depart;
    private String  teacher_status;

    public Teachers() {

    }

    public Teachers(Integer teacher_id, String teacher_name, String techer_depart, String teacher_status) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.techer_depart = techer_depart;
        this.teacher_status = teacher_status;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTecher_depart() {
        return techer_depart;
    }

    public void setTecher_depart(String techer_depart) {
        this.techer_depart = techer_depart;
    }

    public String getTeacher_status() {
        return teacher_status;
    }

    public void setTeacher_status(String teacher_status) {
        this.teacher_status = teacher_status;
    }
}
