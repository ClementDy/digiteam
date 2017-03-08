package glp.digiteam.entity.offer;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import org.springframework.format.annotation.DateTimeFormat;

import glp.digiteam.entity.student.Student;

@Entity
public class Contract {

	@Id
	private long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Student student;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private StaffLille1 staffLille1;
	
	private String useName;

	private String numSecuriteSociale;
	
	private String title;
	
	private String service;
	
	private String mission;
	
	private int hours;
	
	private int taux;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date startDate;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date endDate;
	
	private String responsable;
	
	private  String centreCout;
	
	private String domaineFonctionel;
	
	private String convention_eOTP;

	public Contract() {
		// TODO Auto-generated constructor stub
	}
	
	
	


	public StaffLille1 getStaffLille1() {
		return staffLille1;
	}





	public void setStaffLille1(StaffLille1 staffLille1) {
		this.staffLille1 = staffLille1;
	}





	public String getResponsable() {
		return responsable;
	}


	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getService() {
		return service;
	}


	public void setService(String service) {
		this.service = service;
	}


	public String getMission() {
		return mission;
	}


	public void setMission(String mission) {
		this.mission = mission;
	}


	public int getHours() {
		return hours;
	}


	public void setHours(int hours) {
		this.hours = hours;
	}


	public int getTaux() {
		return taux;
	}


	public void setTaux(int taux) {
		this.taux = taux;
	}


	public String getUseName() {
		return useName;
	}

	public void setUseName(String useName) {
		this.useName = useName;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getNumSecuriteSociale() {
		return numSecuriteSociale;
	}

	public void setNumSecuriteSociale(String numSecuriteSociale) {
		this.numSecuriteSociale = numSecuriteSociale;
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

	public String getCentreCout() {
		return centreCout;
	}

	public void setCentreCout(String centreCout) {
		this.centreCout = centreCout;
	}

	public String getDomaineFonctionel() {
		return domaineFonctionel;
	}

	public void setDomaineFonctionel(String domaineFonctionel) {
		this.domaineFonctionel = domaineFonctionel;
	}

	public String getConvention_eOTP() {
		return convention_eOTP;
	}

	public void setConvention_eOTP(String convention_eOTP) {
		this.convention_eOTP = convention_eOTP;
	}
	
	
}

