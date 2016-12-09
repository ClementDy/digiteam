package Entity;

import java.util.ArrayList;
import java.util.List;

public class Wish {

	private List<Mission> missions = new ArrayList<Mission>();
	private String otherWish;
	private Student student;
	
	public Wish() {
		// TODO Auto-generated constructor stub
	}

	public List<Mission> getMissions() {
		return missions;
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
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
