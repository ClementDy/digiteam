package glp.digiteam.entity.student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import glp.digiteam.entity.offer.Contract;

@Entity
public class Student {

	@Id
	private Integer nip;
	private int civilite;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String nationality;
	private String cv;
	private String statut;
	private int emailSubscribe;
	private Date publicationDate;

	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String motivation;

	private String visa;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateVisa;

	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
	private Address address;

	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
	private Availability availability;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<ExternalContract> externalContracts = new ArrayList<>();

	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
	private Miscellaneous misc;

	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
	private Wish wish;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "student", cascade = CascadeType.ALL)
	List<Training> trainings = new ArrayList<Training>();
	
	@OneToMany(mappedBy="student", cascade=CascadeType.ALL,targetEntity=Contract.class)
	List<Contract> contract = new ArrayList<Contract>();
	
	public Student() {
		this.address = new Address(this);
		this.availability = new Availability(this);
		this.misc = new Miscellaneous(this);
		this.wish = new Wish(this);
		for (int i = 0; i < 5; i++) {
			externalContracts.add(new ExternalContract(this));
		}
		if (trainings.size() != 6) {
			for (int i = 0; i < 6; i++) {
				trainings.add(new Training(this));
			}
		}
	}

	public Student(String firstName, String lastName, String phone, String email, String nationality, String motivation,
			Wish wish) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.nationality = nationality;
		this.motivation = motivation;
		this.wish = wish;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	public List<ExternalContract> getExternalContracts() {
		return externalContracts;
	}

	public void setExternalContracts(List<ExternalContract> externalContracts) {
		this.externalContracts = externalContracts;
	}

	public Miscellaneous getMisc() {
		return misc;
	}

	public void setMisc(Miscellaneous misc) {
		this.misc = misc;
	}

	public Wish getWish() {
		return wish;
	}

	public void setWish(Wish wish) {
		this.wish = wish;
	}

	public Integer getNip() {
		return nip;
	}

	public void setNip(Integer nip) {
		this.nip = nip;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getEmailSubscribe() {
		return emailSubscribe;
	}

	public void setEmailSubscribe(int emailSubscribe) {
		this.emailSubscribe = emailSubscribe;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getMotivation() {
		return motivation;
	}

	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}

	public String getVisa() {
		return visa;
	}

	public void setVisa(String visa) {
		this.visa = visa;
	}

	public java.util.Date getDateVisa() {
		return dateVisa;
	}

	public void setDateVisa(java.util.Date dateVisa) {
		this.dateVisa = dateVisa;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public List<Training> getTrainings() {
		return trainings;
	}

	public void setTrainings(List<Training> trainings) {
		this.trainings = trainings;
	}

	public int getCivilite() {
		return civilite;
	}

	public void setCivilite(int civilite) {
		this.civilite = civilite;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	
	public List<Contract> getContract() {
		return contract;
	}

	public void setContract(List<Contract> contract) {
		this.contract = contract;
	}

	
	
	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	@Override
	public String toString() {
		return "Student [nip=" + nip + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", nationality=" + nationality + ", motivation=" + motivation + ", visa=" + visa + ", dateVisa="
				+ dateVisa + "]";
	}
}
