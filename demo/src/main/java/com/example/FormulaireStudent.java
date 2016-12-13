package com.example;

import java.util.Date;

public class FormulaireStudent {

	
	private String firstName;
	private String lastName;
	private int phone;
	private String email;
	private String nationality;
	private String motivation;
	private boolean visa;
	private Date dateVisa;
	
	private String street;
	private String complement;
	private int postalCode;
	private String City;
	
	public FormulaireStudent(){
		
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}

	@Override
	public String toString() {
		return "FormulaireStudent [firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", email="
				+ email + ", nationality=" + nationality + ", motivation=" + motivation + ", visa=" + visa
				+ ", dateVisa=" + dateVisa + ", street=" + street + ", complement=" + complement + ", postalCode="
				+ postalCode + ", City=" + City + "]";
	}
	
	
}
