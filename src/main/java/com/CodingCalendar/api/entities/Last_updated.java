package com.CodingCalendar.api.entities;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "last_update")
public class Last_updated {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime update_timestamp;

	public Last_updated() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Last_updated(LocalDateTime update_timestamp) {
		super();
		this.update_timestamp = update_timestamp;
	}

	@Override
	public String toString() {
		return "last_update [update_timestamp=" + update_timestamp + ", getUpdate_timestamp()=" + getUpdate_timestamp()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public LocalDateTime getUpdate_timestamp() {
		return update_timestamp;
	}

	public void setUpdate_timestamp(LocalDateTime update_timestamp) {
		this.update_timestamp = update_timestamp;
	}

}
