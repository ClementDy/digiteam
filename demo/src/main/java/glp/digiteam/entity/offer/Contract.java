package glp.digiteam.entity.offer;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import glp.digiteam.entity.student.Student;

@Entity
public class Contract {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Student student;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private StaffLille1 referent;

	private String useName;

	private String numSecuriteSociale;

	private String nature;
	
	@Lob
	@Type(type="org.hibernate.type.TextType")
	private String mission;

	private int hours;

	private double rate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date bornDate;

	private String bornPlace;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date startDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date endDate;

	private String responsable;

	private String costCenter;

	private String domaineFonctionel;

	private String convention_eOTP;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateSaisie;

	public Contract() {
		// TODO Auto-generated constructor stub
	}

	public StaffLille1 getReferent() {
		return referent;
	}

	public String getBornPlace() {
		return bornPlace;
	}

	public void setBornPlace(String bornPlace) {
		this.bornPlace = bornPlace;
	}

	public void setReferent(StaffLille1 referent) {
		this.referent = referent;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public Date getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(Date dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String title) {
		this.nature = title;
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

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
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

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
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
