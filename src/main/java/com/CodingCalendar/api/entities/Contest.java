package com.CodingCalendar.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "competitivecalendarnew")
public class Contest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String platform;
	private String Name;
	private Date Start_time;
	private Date End_time;
	
	public Contest(String platform, String name, String start_time, String end_time) {
		super();
		this.platform = platform;
		Name = name;
		SimpleDateFormat formatter1=new SimpleDateFormat("dd MMM yyy\nHH:mm:ss");  
	    Date sdatetime;
		try {
			sdatetime = formatter1.parse(start_time);
			Start_time = sdatetime;
		} catch (ParseException e1) {
			
			e1.printStackTrace();
		}  
	    Date edatetime;
		try {
			edatetime = formatter1.parse(end_time);
			End_time = edatetime;
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		
	}
	public Contest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "ContestSQL [id=" + id + ", platform=" + platform + ", Name=" + Name + ", Start_time=" + Start_time
				+ ", End_time=" + End_time + ", getId()=" + getId() + ", getPlatform()=" + getPlatform()
				+ ", getName()=" + getName() + ", getStart_time()=" + getStart_time() + ", getEnd_time()="
				+ getEnd_time() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
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
