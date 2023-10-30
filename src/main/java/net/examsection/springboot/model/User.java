package net.examsection.springboot.model;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Student")
@DynamicUpdate
public class User {
	
	@Id
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