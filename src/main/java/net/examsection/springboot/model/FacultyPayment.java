package net.examsection.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "faculty_payment")
public class FacultyPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String facultyName;
    private String year;
    private String std;
    private String sem;
    private String exam;
    private String facultyStatus;

    @Transient
    private int noOfSupervisions;

    @Transient
    private double totalAmount;

    public FacultyPayment() {
    }

    public FacultyPayment(String facultyName, String year, String std, String sem, String exam, String facultyStatus) {
        this.facultyName = facultyName;
        this.year = year;
        this.std = std;
        this.sem = sem;
        this.exam = exam;
        this.facultyStatus = facultyStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getFacultyStatus() {
        return facultyStatus;
    }

    public void setFacultyStatus(String facultyStatus) {
        this.facultyStatus = facultyStatus;
    }

    public int getNoOfSupervisions() {
        return noOfSupervisions;
    }

    public void setNoOfSupervisions(int noOfSupervisions) {
        this.noOfSupervisions = noOfSupervisions;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
//    	System.out.println("getting :"+totalAmount);
        this.totalAmount = totalAmount;
    }
}
