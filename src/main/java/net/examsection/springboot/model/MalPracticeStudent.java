package net.examsection.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MalPracticeStudent {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private Double prn;
    private String studentname;
    private String programname;
    private Integer block_no;
    private String date;
    private String course;
    private String coursecode;
    private String year;
    private String examyear;
    private String examination;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Double getPrn() {
        return prn;
    }
    public void setPrn(Double prn) {
        this.prn = prn;
    }
    public String getStudentname() {
        return studentname;
    }
    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
    public String getProgramname() {
        return programname;
    }
    public void setProgramname(String programname) {
        this.programname = programname;
    }
    public Integer getBlock_no() {
        return block_no;
    }
    public void setBlock_no(Integer block_no) {
        this.block_no = block_no;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public String getCoursecode() {
        return coursecode;
    }
    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getAcademic_year() {
        return examyear;
    }
    public void setAcademic_year(String academic_year) {
        this.examyear = academic_year;
    }
    public String getExamination() {
        return examination;
    }
    public void setExamination(String examination) {
        this.examination = examination;
    }



}
