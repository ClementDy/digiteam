package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

public class Availability {

	private Date startDate;
	private Date endDate;
	private List<AvailabilityDay> days = new ArrayList<AvailabilityDay>();
	private Student student;
	
	public Availability() {
		// TODO Auto-generated constructor stub
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

	public List<AvailabilityDay> getDays() {
		return days;
	}

	public void setDays(List<AvailabilityDay> days) {
		this.days = days;
	}
	
	public void addAvailabilityDay(AvailabilityDay day){
		this.days.add(day);
	}
	
	public void removeAvailabilityDay(AvailabilityDay day){
		this.days.remove(day);
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
}
