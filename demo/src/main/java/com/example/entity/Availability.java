package com.example.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Availability {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne
	private Student student;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date startDate = new Date();
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date endDate = new Date();
	// private List<AvailabilityDay> days = new ArrayList<AvailabilityDay>();

	@DateTimeFormat(pattern = "HH:mm")
	private Date startTimeMonday;
	@DateTimeFormat(pattern = "HH:mm")
	private Date endTimeMonday;

	public Availability() {

	}

	public Availability(Student student) {
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

	public int getStartTimeTuesday() {
		return startTimeTuesday;
	}

	public void setStartTimeTuesday(int startTimeTuesday) {
		this.startTimeTuesday = startTimeTuesday;
	}

	private int startTimeTuesday;
	private int endTimeTuesday;

	public int getEndTimeTuesday() {
		return endTimeTuesday;
	}

	public void setEndTimeTuesday(int endTimeTuesday) {
		this.endTimeTuesday = endTimeTuesday;
	}

	
	public Date getStartTimeMonday() {
		return startTimeMonday;
	}

	public void setStartTimeMonday(Date startTimeMonday) {
		this.startTimeMonday = startTimeMonday;
	}

	public Date getEndTimeMonday() {
		return endTimeMonday;
	}

	public void setEndTimeMonday(Date endTimeMonday) {
		this.endTimeMonday = endTimeMonday;
	}

	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Availability [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
}
