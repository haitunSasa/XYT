package com.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NotificationClass entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notification_class", catalog = "XYT")
public class NotificationClass implements java.io.Serializable {

	// Fields

	private Integer ncId;
	private Integer notificationId;
	private Integer classId;

	// Constructors

	/** default constructor */
	public NotificationClass() {
	}

	/** full constructor */
	public NotificationClass(Integer notificationId, Integer classId) {
		this.notificationId = notificationId;
		this.classId = classId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ncId", unique = true, nullable = false)
	public Integer getNcId() {
		return this.ncId;
	}

	public void setNcId(Integer ncId) {
		this.ncId = ncId;
	}

	@Column(name = "notificationId", nullable = false)
	public Integer getNotificationId() {
		return this.notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	@Column(name = "classId", nullable = false)
	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

}