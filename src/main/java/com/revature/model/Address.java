package com.revature.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
@Entity
@Table(name="addresses") // this just add's the name of the table -- otherwise the mapped table takes on the name of the class.
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(exclude = {"owners"}) @ToString(exclude= {"owners"}) // this prevents recursive loop which happens in objects with a bi-directional relationhship
public class Address { // automatically hibernate will generate a table with the name of this class in lowercase
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	@JsonView({ JsonViewProfiles.User.class, JsonViewProfiles.Address.class })
	private int id;
	
	private String street; // hibernate will automatically do this for us! "12 Main st."
	private String secondary;  //"Apt. A"
	
	@Length(min = 2, max=2)
	private String state;
	
	private String city;

	@JsonView(JsonViewProfiles.Address.class)
	@ManyToMany(mappedBy = "addresses")
	private Set<User> owners; // https://stackoverflow.com/questions/67886252/spring-boot-jpa-infinite-loop-many-to-many

	public Address(String street, String secondary, @Length(min = 2, max = 2) String state, String city) {
		super();
		this.street = street;
		this.secondary = secondary;
		this.state = state;
		this.city = city;
	}
	
	

}
