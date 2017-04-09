package com.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TeacherClass entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "teacher_class", catalog = "XYT")
public class TeacherClass implements java.io.Serializable {

	// Fields

	private Integer tcId;
	private Integer userId;
	private Integer classId;

	// Constructors

	/** default constructor */
	public TeacherClass() {
	}

	/** full constructor */
	public TeacherClass(Integer userId, Integer classId) {
		this.userId = userId;
		this.classId = classId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tcId", unique = true, nullable = false)
	public Integer getTcId() {
		return this.tcId;
	}

	public void setTcId(Integer tcId) {
		this.tcId = tcId;
	}

	@Column(name = "userId", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "classId", nullable = false)
	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

}