package com.aldeamo.bartender.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Arrays {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	private String input_array;
	
	public Arrays() {
		// TODO Auto-generated constructor stub
	}
	
	public Arrays(int id, String inputArray) {
		this.id = id;
		this.input_array = inputArray;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInputArray() {
		return input_array;
	}

	public void setInputArray(String inputArray) {
		this.input_array = inputArray;
	}

	

}
