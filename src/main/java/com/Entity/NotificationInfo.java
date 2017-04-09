package com.Entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NotificationInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notification_info", catalog = "XYT")
public class NotificationInfo implements java.io.Serializable {

	// Fields

	private Integer notificationId;
	private Integer teacherId;
	private String content;
	private Date assignTime;

	// Constructors

	/** default constructor */
	public NotificationInfo() {
	}

	/** minimal constructor */
	public NotificationInfo(Integer notificationId, Integer teacherId) {
		this.notificationId = notificationId;
		this.teacherId = teacherId;
	}

	/** full constructor */
	public NotificationInfo(Integer notificationId, Integer teacherId,
			String content, Date assignTime) {
		this.notificationId = notificationId;
		this.teacherId = teacherId;
		this.content = content;
		this.assignTime = assignTime;
	}

	// Property accessors
	@Id
	@Column(name = "notificationId", unique = true, nullable = false)
	public Integer getNotificationId() {
		return this.notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	@Column(name = "teacherId", nullable = false)
	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	@Column(name = "content", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "assignTime", length = 19)
	public Date getAssignTime() {
		return this.assignTime;
	}

	public void setAssignTime(Date assignTime) {
		this.assignTime = assignTime;
	}

}