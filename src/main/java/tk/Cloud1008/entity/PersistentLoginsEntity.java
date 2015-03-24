package tk.Cloud1008.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PERSISTENTLOGINS")
public class PersistentLoginsEntity {

    @Id
    @Column(name="ID")
    @GeneratedValue
    private Integer id;

	@Column(name="USERNAME")
    private String username;
    
    @Column(name="SERIES")
    private String series;
	
    @Column(name="TOKEN")
    private String token;
    
    @Column(name="LASTUSED")
    private String LastUsed;    
    
    
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

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLastUsed() {
		return LastUsed;
	}

	public void setLastUsed(String LastUsed) {
		this.LastUsed = LastUsed;
	}

}
