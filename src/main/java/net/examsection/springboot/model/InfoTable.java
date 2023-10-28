package net.examsection.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "InfoTable")
public class InfoTable {
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
    
    @Column(name = "CourseCode")
    private String CourseCode;
    
    public InfoTable() {

    }
	public InfoTable(int id, String option, String date, String year, String subject,
			String startTime, String course, String CourseCode) {
		super();
		this.id = id;
		this.option = option;
		this.date = date;
		this.CourseCode=CourseCode;
		this.year = year;
		this.subject = subject;
		this.startTime = startTime;
		this.course = course;
	}
	
	public String getCourseCode() {
		return CourseCode;
	}
	public void setCourseCode(String courseCode) {
		CourseCode = courseCode;
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
