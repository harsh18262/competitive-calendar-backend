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
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String Platform;
	private String Name;
	private Date Start_time;
	private Date End_time;
	
	public Contest(String platform, String name, Date start_time, Date end_time) {
		super();
		Platform = platform;
		Name = name;
		Start_time = start_time;
		End_time = end_time;
		
	}
	public Contest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "ContestSQL [id=" + id + ", platform=" + Platform + ", Name=" + Name + ", Start_time=" + Start_time
				+ ", End_time=" + End_time + ", getId()=" + getId() + ", getPlatform()=" + getPlatform()
				+ ", getName()=" + getName() + ", getStart_time()=" + getStart_time() + ", getEnd_time()="
				+ getEnd_time() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
