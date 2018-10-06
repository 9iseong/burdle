package com.reactive.demo.users;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Data
@Table(name = "users")
public class Users {
	@Id
	private String userId;
	private String password;
	private String email;
	private String userName;
	
}
