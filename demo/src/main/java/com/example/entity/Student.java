package com.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

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
	private boolean visa;
	private Date dateVisa;
	//private Wish wish;
	//private Availability availabity;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Miscellaneous misc;
	
	public Student() {
		this.address = new Address();
		this.misc= new Miscellaneous();
	}
	
	


	public Student(String firstName, String lastName, int phone, String email, String nationality,String motivation) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.nationality = nationality;
		this.motivation=motivation;
	}

	

	/*public Wish getWish() {
		return wish;
	}

	public void setWish(Wish wish) {
		this.wish = wish;
	}

	public Availability getAvailabity() {
		return availabity;
	}

	public void setAvailabity(Availability availabity) {
		this.availabity = availabity;
	}
*/
	public Miscellaneous getMisc() {
		return misc;
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




	public boolean isVisa() {
		return visa;
	}




	public void setVisa(boolean visa) {
		this.visa = visa;
	}




	public Date getDateVisa() {
		return dateVisa;
	}




	public void setDateVisa(Date dateVisa) {
		this.dateVisa = dateVisa;
	}




	@Override
	public String toString() {
		return "Student [nip=" + nip + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", nationality=" + nationality + ", motivation=" + motivation + ", visa=" + visa + ", dateVisa="
				+ dateVisa + "]";
	}
	
	
}
