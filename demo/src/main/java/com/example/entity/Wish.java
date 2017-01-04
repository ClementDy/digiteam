package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Wish {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@OneToOne
	private Student student;
	
	@OneToMany
	private List<Mission> missions = new ArrayList<Mission>();
	private String otherWish;
	
	
	public Wish() {
		
	}
	
	public Wish(Student student) {
		this.student = student;
	}

	public Wish(List<Mission> missions, String otherWish) {
		this.missions = missions;
		this.otherWish = otherWish;
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
	

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}

	public String getOtherWish() {
		return otherWish;
	}

	public void setOtherWish(String otherWish) {
		this.otherWish = otherWish;
	}
	
	public void addMission(Mission mission){
		this.missions.add(mission);
	}
	
	public void removeMission(Mission mission){
		this.missions.remove(mission);
	}
	
	public List<Mission> getMissions() {
		return missions;
	}
}
