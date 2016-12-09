package Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Availability {

	private Date startDay;
	private Date endDate;
	private List<AvailabilityDay> days = new ArrayList<AvailabilityDay>();
	private Student student;
	
	public Availability() {
		// TODO Auto-generated constructor stub
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
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
