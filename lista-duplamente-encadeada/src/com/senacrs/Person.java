package com.senacrs;

import java.util.List;

public class Person {
	private String name;
	private String address;
	private List<String> phone_numbers;
	
	public Person(String name, String address, List<String> phone_numbers){
		this.name = name;
		this.address = address;
		this.phone_numbers = phone_numbers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getPhone_numbers() {
		return phone_numbers;
	}

	public void setPhone_numbers(List<String> phone_numbers) {
		this.phone_numbers = phone_numbers;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String phones = "";
		for(String phone: this.phone_numbers) {
			phones = phones + phone + " ";
		}
		return "Name: " + this.name + " Address: " + this.address + " Phones: " + phones + "\n";
	}
}
