package com.denorro.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Developer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	private String password;
	
	@Column(unique=true, nullable=false)
	@Size(min=4,max=25)
	private String username;
	
	private String city;
	
	private String state;
	
	private String country = "Unknown";
	
	private boolean available = false;
	
	private BigDecimal rate;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="developer_technology", 
			   joinColumns= {@JoinColumn(name="developer_id")}, 
			   inverseJoinColumns= {@JoinColumn(name="technology_id")})
	@JsonIgnore
	private Set<Technology> skills = new HashSet<Technology>();
	
	@CreationTimestamp
	private Date created;
	
	@UpdateTimestamp
	private Date updated;
	
	public Developer() {super();}
	
	public Developer(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Developer(String firstName, String lastName, @Size(min = 4, max = 25) String username,
			@NotNull String country, boolean available) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.country = country;
		this.available = available;
	}
	
	public void addTechnologyToDeveloper(Technology tech) {
		this.skills.add(tech);
		tech.getDevelopers().add(this);
	}
	
	public void removeTechnologyFromDeveloper(Technology tech) {
		this.skills.remove(tech);
		tech.getDevelopers().remove(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Set<Technology> getSkills() {
		return skills;
	}

	public void setSkills(Set<Technology> skills) {
		this.skills = skills;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Developer other = (Developer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Developer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", available=" + available
				+ ", rate=" + rate + "]";
	}
}
