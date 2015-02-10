package com.globant.carrito.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class User {
	
	@GeneratedValue
	@Id	
	private int usr_id;
	
	@Column
	private String usr_name;
	
	@Column
	private  String usr_pass;
	
	@Column
	private String usr_mail;

	
	public User(String usr_name, String usr_pass, String usr_mail) {
		super();
		this.usr_name = usr_name;
		this.usr_pass = usr_pass;
		this.usr_mail = usr_mail;
	}

	public int getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(int usr_id) {
		this.usr_id = usr_id;
	}

	public String getUsr_name() {
		return usr_name;
	}

	public void setUsr_name(String usr_name) {
		this.usr_name = usr_name;
	}

	public String getUsr_pass() {
		return usr_pass;
	}

	public void setUsr_pass(String usr_pass) {
		this.usr_pass = usr_pass;
	}

	public String getUsr_mail() {
		return usr_mail;
	}

	public void setUsr_mail(String usr_mail) {
		this.usr_mail = usr_mail;
	}
	
			
	
}
