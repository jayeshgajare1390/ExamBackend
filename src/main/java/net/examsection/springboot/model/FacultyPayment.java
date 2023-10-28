package net.examsection.springboot.model;


import jakarta.persistence.*;

@Entity
@Table(name = "faculty_payment")
public class FacultyPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String facultyName;
    private String year;
    private String std;
    private String exam;
    private String facultyStatus;

    @Transient
    private int noOfSupervisions;

    @Transient
    private double totalAmount;

    public FacultyPayment() {
    }

    public FacultyPayment(String facultyName, String year, String std, String exam, String facultyStatus) {
        this.facultyName = facultyName;
        this.year = year;
        this.std = std;
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
