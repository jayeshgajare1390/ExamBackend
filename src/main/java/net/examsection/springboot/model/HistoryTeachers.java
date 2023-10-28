package net.examsection.springboot.model;

import jakarta.persistence.*;

@Entity

public class HistoryTeachers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer teacher_id;


    private String teacher_name;


    private String  teacher_Status;


    private String exam_date;
    private int block_no;

    public HistoryTeachers(Integer teacher_id, String teacher_name, String teacher_Status, String exam_date, int block_no) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.teacher_Status = teacher_Status;
        this.exam_date = exam_date;
        this.block_no = block_no;
    }

    public HistoryTeachers(){

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

    public String getTeacher_Status() {
        return teacher_Status;
    }

    public void setTeacher_Status(String teacher_Status) {
        this.teacher_Status = teacher_Status;
    }

    public String getExam_date() {
        return exam_date;
    }

    public void setExam_date(String exam_date) {
        this.exam_date = exam_date;
    }

    public int getBlock_no() {
        return block_no;
    }

    public void setBlock_no(int block_no) {
        this.block_no = block_no;
    }
}
