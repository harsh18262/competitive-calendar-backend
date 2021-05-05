package com.CodingCalendar.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "competitivecalendarnew")
public class Contest {
	
	public Contest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String Platform;
	private String Name;
	private Date Start_time;
	private Date End_time;
	private String Url;
	public Integer getId() {
		return id;
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
	public Contest(String platform, String name, Date start_time, Date end_time, String url) {
		super();
		Platform = platform;
		Name = name;
		Start_time = start_time;
		End_time = end_time;
		Url = url;
	}
	@Override
	public String toString() {
		return "Contest [id=" + id + ", Platform=" + Platform + ", Name=" + Name + ", Start_time=" + Start_time
				+ ", End_time=" + End_time + ", Url=" + Url + ", getId()=" + getId() + ", getPlatform()="
				+ getPlatform() + ", getName()=" + getName() + ", getStart_time()=" + getStart_time()
				+ ", getEnd_time()=" + getEnd_time() + ", getUrl()=" + getUrl() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

		
}
