/*package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Trainings {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@OneToOne
	private Student student;

	@OneToMany
	private List<Training> trainings = new ArrayList<Training>();


	public Trainings(){

	}

	public Trainings(Student student){
		this.student=student;
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


	public List<Training> getTrainings() {
		return trainings;
	}


	public void setTrainings(List<Training> trainings) {
		this.trainings = trainings;
	}

	public void addTraining(Training training){
		this.trainings.add(training);
	}

	public void removeMission(Training training){
		this.trainings.remove(training);
	}


}*/
