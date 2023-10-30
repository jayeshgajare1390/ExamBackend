package net.examsection.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Teacher1 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int teacher_id;
    private String exam_date;
    private String day;


    private String startingtime;
    private String endingtime;
    private Integer block_no;
    public Teacher1(){

    }

    public Teacher1(int teacher_id, String exam_date, String day, String startingtime, String endingtime, Integer block_no) {
        this.teacher_id = teacher_id;
        this.exam_date = exam_date;
        this.day = day;
        this.startingtime = startingtime;
        this.endingtime = endingtime;
        this.block_no = block_no;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getExam_date() {
        return exam_date;
    }

    public void setExam_date(String exam_date) {
        this.exam_date = exam_date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartingtime() {
        return startingtime;
    }

    public void setStartingtime(String startingtime) {
        this.startingtime = startingtime;
    }

    public String getEndingtime() {
        return endingtime;
    }

    public void setEndingtime(String endingtime) {
        this.endingtime = endingtime;
    }

    public Integer getBlock_no() {
        return block_no;
    }

    public void setBlock_no(Integer block_no) {
        this.block_no = block_no;
    }
}
