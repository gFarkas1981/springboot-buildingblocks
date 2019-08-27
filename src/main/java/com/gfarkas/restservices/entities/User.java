package com.gfarkas.restservices.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "user")
//@JsonIgnoreProperties({"firstname", "lastname"}) -- Static Filtering JsonIgnore
//@JsonFilter(value = "userFilter") -- Used for MappingJacksonValue filtering section
public class User extends ResourceSupport {

	@Id
	@GeneratedValue
	@JsonView(Views.External.class)
	private long userid;

	@NotEmpty(message = "Username is Mandatory, please provide one!")
	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	@JsonView(Views.External.class)
	private String username;

	@Size(min = 2, message = "First name must be at least 2 charcters")
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String firstname;

	@Column(name = "LAST_NAME", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String lastname;

	@Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String email;

	@Column(name = "ROLE", length = 50, nullable = false)
	@JsonView(Views.Internal.class)
	private String role;

	@Column(name = "SSN", length = 50, nullable = false, unique = true)
	//@JsonIgnore -- Static Filtering JsonIgnore
	@JsonView(Views.Internal.class)
	private String ssn;

	@OneToMany(mappedBy = "user")
	@JsonView(Views.Internal.class)
	private List<Order> orders;

	// No argument constructor
	public User() {
	}

	// Fields constructor
	public User(long userid, String username, String firstname, String lastname, String email, String role,
			String ssn) {
		super();
		this.userid = userid;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}

	// Getters and setters
	public long getUserId() {
		return userid;
	}

	public void setUserId(long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	// To stringS
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}

}
