package glp.digiteam.entity.student;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ExternalContract {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Student student;
	
	private String employer;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date startDate;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date endDate;
	
	private Integer hours;
	
	private String missions;
	
	
	public ExternalContract() {
		
	}
	
	public ExternalContract(Student student) {
		this.student = student;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	
	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public String getMissions() {
		return missions;
	}

	public void setMissions(String missions) {
		this.missions = missions;
	}
}
