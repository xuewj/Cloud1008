package tk.Cloud1008.entity;

import java.io.UnsupportedEncodingException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NUMBERPLATES")
public class NumberPlateEntity {
     
    @Id
    @Column(name="ID")
    @GeneratedValue
    private Integer id;
    
	@Column(name="IMAGE")
    private String image;
 
    @Column(name="FILENAME")
    private String FileName;
 
    @Column(name="CONTENTTYPE")
    private String ContenType;

	@Column(name="OWNER")
    private String Owner;
	
	@Column(name="NUMBER")
	private String number;
	
	@Column(name="DETAILS")
	private String Details;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
    
    public String getDetails() {
		return Details;
	}
	public void setDetails(String details) {
		Details = details;
	}
	public String getOwner() {
		return Owner;
	}
	public void setOwner(String owner) {
		Owner = owner;
	}
	public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public String getContenType() {
		return ContenType;
	}
	public void setContenType(String contenType) {
		ContenType = contenType;
	}

}