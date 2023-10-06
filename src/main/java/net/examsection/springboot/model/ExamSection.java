package net.examsection.springboot.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="studentdataexam")
public class ExamSection {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private long id;
	@Column(name="prn")
	private Double prn;
	@Column(name="studentname")
	private String studentname;
	@Column(name="programname")
	private String programname;

	@Column(name="info_table_id")
	private Integer infoTableId;
	@Column(name="block_no")
	private Integer block_no;
	public ExamSection()
	{}
	public ExamSection(long id, Double prn, String studentname, String programname, Integer infoTableId,
			Integer block_no) {
		super();
		this.id = id;
		this.prn = prn;
		this.studentname = studentname;
		this.programname = programname;
		this.infoTableId = infoTableId;
		this.block_no = block_no;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public Integer getInfoTableId() {
		return infoTableId;
	}
	public void setInfoTableId(Integer infoTableId) {
		this.infoTableId = infoTableId;
	}
	public Integer getBlock_no() {
		return block_no;
	}
	public void setBlock_no(Integer block_no) {
		this.block_no = block_no;
	}
}
