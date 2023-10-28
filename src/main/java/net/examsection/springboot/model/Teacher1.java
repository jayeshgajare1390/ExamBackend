package net.examsection.springboot.model;

import jakarta.persistence.*;

@Entity

public class Teacher1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "optionvalue")
    private String option;

    @Column(name = "date")
    private String date;

    @Column(name = "year")
    private String year;


    @Column(name = "subject")
    private String subject;

    @Column(name = "slot")
    private String startTime;

    @Column(name = "course")
    private String course;

    public Teacher1(){

    }

    public Teacher1(int id, String option, String date, String year, String subject, String startTime, String course) {
        this.id = id;
        this.option = option;
        this.date = date;
        this.year = year;
        this.subject = subject;
        this.startTime = startTime;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
