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
	
	@DateTimeFormat(pattern = "HH:mm")
	private Date startTimeTuesday;
	@DateTimeFormat(pattern = "HH:mm")
	private Date endTimeTuesday;
	
	@DateTimeFormat(pattern = "HH:mm")
	private Date startTimeWednesday;
	@DateTimeFormat(pattern = "HH:mm")
	private Date endTimeWednesday;
	
	@DateTimeFormat(pattern = "HH:mm")
	private Date startTimeThursday;
	@DateTimeFormat(pattern = "HH:mm")
	private Date endTimeThursday;
	
	@DateTimeFormat(pattern = "HH:mm")
	private Date startTimeFriday;
	@DateTimeFormat(pattern = "HH:mm")
	private Date endTimeFriday;

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

	
	
	
	public Date getStartTimeTuesday() {
		return startTimeTuesday;
	}

	public void setStartTimeTuesday(Date startTimeTuesday) {
		this.startTimeTuesday = startTimeTuesday;
	}

	public Date getEndTimeTuesday() {
		return endTimeTuesday;
	}

	public void setEndTimeTuesday(Date endTimeTuesday) {
		this.endTimeTuesday = endTimeTuesday;
	}

	public Date getStartTimeWednesday() {
		return startTimeWednesday;
	}

	public void setStartTimeWednesday(Date startTimeWednesday) {
		this.startTimeWednesday = startTimeWednesday;
	}

	public Date getEndTimeWednesday() {
		return endTimeWednesday;
	}

	public void setEndTimeWednesday(Date endTimeWednesday) {
		this.endTimeWednesday = endTimeWednesday;
	}

	public Date getStartTimeThursday() {
		return startTimeThursday;
	}

	public void setStartTimeThursday(Date startTimeThursday) {
		this.startTimeThursday = startTimeThursday;
	}

	public Date getEndTimeThursday() {
		return endTimeThursday;
	}

	public void setEndTimeThursday(Date endTimeThursday) {
		this.endTimeThursday = endTimeThursday;
	}

	public Date getStartTimeFriday() {
		return startTimeFriday;
	}

	public void setStartTimeFriday(Date startTimeFriday) {
		this.startTimeFriday = startTimeFriday;
	}

	public Date getEndTimeFriday() {
		return endTimeFriday;
	}

	public void setEndTimeFriday(Date endTimeFriday) {
		this.endTimeFriday = endTimeFriday;
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
		return "Availability [id=" + id + ", student=" + student + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", startTimeMonday=" + startTimeMonday + ", endTimeMonday=" + endTimeMonday + ", startTimeTuesday="
				+ startTimeTuesday + ", endTimeTuesday=" + endTimeTuesday + ", startTimeWednesday=" + startTimeWednesday
				+ ", endTimeWednesday=" + endTimeWednesday + ", startTimeThursday=" + startTimeThursday
				+ ", endTimeThursday=" + endTimeThursday + ", startTimeFriday=" + startTimeFriday + ", endTimeFriday="
				+ endTimeFriday + "]";
	}


	
}
