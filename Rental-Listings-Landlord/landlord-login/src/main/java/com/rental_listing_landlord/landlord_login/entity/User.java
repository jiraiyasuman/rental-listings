package com.rental_listing_landlord.landlord_login.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="email",nullable=false,unique=true)
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="name")
	private String name;
	@Column(name="failed_attempts")
	private int failedAttempts;
	@Column(name="lock_local_date_time")
	private LocalDateTime lockLocalDateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFailedAttempts() {
		return failedAttempts;
	}
	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}
	public LocalDateTime getLockLocalDateTime() {
		return lockLocalDateTime;
	}
	public void setLockLocalDateTime(LocalDateTime lockLocalDateTime) {
		this.lockLocalDateTime = lockLocalDateTime;
	}
	public User(int id, String email, String password, String name, int failedAttempts,
			LocalDateTime lockLocalDateTime) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.failedAttempts = failedAttempts;
		this.lockLocalDateTime = lockLocalDateTime;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", failedAttempts="
				+ failedAttempts + ", lockLocalDateTime=" + lockLocalDateTime + "]";
	}
	
}
