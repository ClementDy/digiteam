package com.example.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long nip;
	private String firstName;
	private String lastName;
	private int phone;
	private String email;
	private String nationality;
	private String motivation;
	private String visa;
	@DateTimeFormat (pattern="dd/MM/yyyy")
	private Date dateVisa = new Date();
	
	@OneToOne(mappedBy="student", cascade=CascadeType.ALL)
	private Address address;

	@OneToOne(mappedBy="student", cascade=CascadeType.ALL)
	private Availability availability;

	@OneToOne(mappedBy="student", cascade=CascadeType.ALL)
	private Miscellaneous misc;
	
	@OneToOne(mappedBy="student", cascade=CascadeType.ALL)
	private Wish wish;
	
	
	public Student() {
		this.address = new Address(this);
		this.availability = new Availability(this);
		this.misc = new Miscellaneous(this);
		this.wish = new Wish(this);
	}
	
	public Student(String firstName, String lastName, int phone, String email, String nationality, String motivation, Wish wish) {
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
	

	public long getNip() {
		return nip;
	}

	public void setNip(long nip) {
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

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
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


	@Override
	public String toString() {
		return "Student [nip=" + nip + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", nationality=" + nationality + ", motivation=" + motivation + ", visa=" + visa + ", dateVisa="
				+ dateVisa + "]";
	}
}
