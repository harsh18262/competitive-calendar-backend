package com.CodingCalendar.api.entities;

import java.util.Date;

public class Contest {
	
	
	private String platform;
	private String Name;
	private Date Start_time;
	private Date End_time;
	public Contest(String platform, String name, Date start_time, Date end_time) {
		super();
		this.platform = platform;
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
		return "Contest [platform=" + platform + ", Name=" + Name + ", Start_time=" + Start_time + ", End_time="
				+ End_time + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
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
	

}
