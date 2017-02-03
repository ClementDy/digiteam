package glp.digiteam.entity.student;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Training {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int date;
	private String place;
	private String name;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Student student;
	
	
	public Training(){
		
	}
	public Training(Student student){
		this.student=student;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	
	
	
	
}
