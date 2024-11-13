/**
 * 
 * I declare that this code was written by me, xandr. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: xandra
 * Student ID: 22022591
 * Date created: 2024-Oct-27 10:27:55 pm 
 * 
 */
package fyp.user.admin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * @author xandr
 *
 */
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message="Name cannot be empty!")
	@Size(min=3, max=50, message="Name must be between 3 to 50 characters long")
	private String name;
	
	@NotEmpty(message="Username cannot be empty!")
	@Size(min=5, max=50, message="Username must be between 5 to 50 characters long")
	private String username;
	
	@NotEmpty(message="Password cannot be empty!")
	@Size(min=1, max=100, message="Password must be between 5 to 50 characters long")
	private String password;
	
	@NotEmpty(message="Email cannot be empty!")
	@Size(min=5, max=50, message="Email must be valid!")
	private String email;
	
	
	private String role;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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


}
