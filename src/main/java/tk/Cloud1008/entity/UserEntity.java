package tk.Cloud1008.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class UserEntity {

    @Id
    @Column(name="ID")
    @GeneratedValue
    private Integer id;
	
	@Column(name="USERNAME")
    private String username;
	
	@Column(name="PASSWORD")
    private String password;
	

	@Column(name="ENABLED")
    private String enabled;
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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


	public String getEnabled() {
		return enabled;
	}


	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

}
