package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Wish {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@OneToMany
	private List<Mission> missions = new ArrayList<Mission>();
	private String otherWish;
	

	
	public Wish() {
		// TODO Auto-generated constructor stub
	}

	
	public Wish(List<Mission> missions, String otherWish) {
		super();
		this.missions = missions;
		this.otherWish = otherWish;
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
