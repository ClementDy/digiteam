package com.example.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

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
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Address address;
	private String nationality;
	private String motivation;
	private String visa;
	@DateTimeFormat
	private java.util.Date dateVisa = new Date();

	@OneToOne
	@PrimaryKeyJoinColumn
	private Wish wish;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Availability availability;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Miscellaneous misc;
	
	public Student() {
		this.address = new Address();
		this.misc= new Miscellaneous();
		this.availability=new Availability();
		this.wish = new Wish();
	}
	
	


	public Student(String firstName, String lastName, int phone, String email, String nationality,String motivation,Wish wish) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.nationality = nationality;
		this.motivation=motivation;
		this.wish = wish;
	}

	

	public Wish getWish() {
		return wish;
	}

	public void setWish(Wish wish) {
		this.wish = wish;
	}
	

	public Miscellaneous getMisc() {
		return misc;
	}

	public Availability getAvailability() {
		return availability;
	}


	public void setAvailability(Availability availability) {
		this.availability = availability;
	}




	public void setMisc(Miscellaneous misc) {
		this.misc = misc;
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




	public Address getAddress() {
		return address;
	}




	public void setAddress(Address address) {
		this.address = address;
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
