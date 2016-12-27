package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Availability {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@DateTimeFormat
	private java.util.Date startDate = new Date();
	@DateTimeFormat
	private java.util.Date endDate = new Date();
	/*private List<AvailabilityDay> days = new ArrayList<AvailabilityDay>();
	private Student student;
	*/
	
	@Override
	public String toString() {
		return "Availability [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	public Availability() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	
	
	
}
