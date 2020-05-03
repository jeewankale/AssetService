package in.co.web.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private String username;
	
	private int contactnum;
	
	private String password;
	
	private Date createdDate;
	
	private String createdBy;
	
	private Date updatedDate;
	
	private String updatedBy;
	
	private boolean isLocked;
	
	private boolean Enable;
	
	
}
