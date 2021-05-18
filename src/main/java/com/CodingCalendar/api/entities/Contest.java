package com.CodingCalendar.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.util.Date;

@Entity
@Table(name = "competitivecalendarnew")
public class Contest {
	
	@Override
	public String toString() {
		return "Contest [id=" + id + ", Platform=" + Platform + ", Name=" + Name + ", Start_time=" + Start_time
				+ ", End_time=" + End_time + ", Url=" + Url + ", Phase=" + Phase + ", createDateTime=" + createDateTime
				+ ", updateDateTime=" + updateDateTime + ", getId()=" + getId() + ", getCreateDateTime()="
				+ getCreateDateTime() + ", getUpdateDateTime()=" + getUpdateDateTime() + ", getPlatform()="
				+ getPlatform() + ", getName()=" + getName() + ", getStart_time()=" + getStart_time()
				+ ", getEnd_time()=" + getEnd_time() + ", getUrl()=" + getUrl() + ", getPhase()=" + getPhase()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public Contest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String Platform;
	private String Name;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date Start_time;
	@Temporal(TemporalType.TIMESTAMP)
	private Date End_time;
	
	private String Url;
	private String Phase;
	
    @CreationTimestamp
    private Date createDateTime;
 
    @UpdateTimestamp
    private Date updateDateTime;
    
	public Integer getId() {
		return id;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Date getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlatform() {
		return Platform;
	}
	public void setPlatform(String platform) {
		Platform = platform;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Date getStart_time() {
		return Start_time;
	}
	public void setStart_time(Date start_time) {
		Start_time = start_time;
	}
	public Date getEnd_time() {
		return End_time;
	}
	public void setEnd_time(Date end_time) {
		End_time = end_time;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}

	public Contest(String platform, String name, Date start_time, Date end_time, String url,String phase) {
		super();
		Platform = platform;
		Name = name;
		Start_time = start_time;
		End_time = end_time;
		Url = url;
		Phase = phase;
		
	}

	public String getPhase() {
		return Phase;
	}

	public void setPhase(String phase) {
		Phase = phase;
	}
	
	public boolean compare(Contest a)
	{
		if( a.getName().equals(this.getName())&&
			a.getStart_time().equals(this.getStart_time())&&
			a.getEnd_time().equals(this.getEnd_time())&&
			a.getUrl().equals(this.getUrl())&&
			a.getPhase().equals(this.getPhase())&&
			a.getPlatform().equals(this.getPlatform())
			)
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	
		
}
