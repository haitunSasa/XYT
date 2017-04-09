package com.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TeacherSubject entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "teacher_subject", catalog = "XYT")
public class TeacherSubject implements java.io.Serializable {

	// Fields

	private Integer tsId;
	private Integer userId;
	private Integer subjectId;

	// Constructors

	/** default constructor */
	public TeacherSubject() {
	}

	/** full constructor */
	public TeacherSubject(Integer userId, Integer subjectId) {
		this.userId = userId;
		this.subjectId = subjectId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tsId", unique = true, nullable = false)
	public Integer getTsId() {
		return this.tsId;
	}

	public void setTsId(Integer tsId) {
		this.tsId = tsId;
	}

	@Column(name = "userId", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "subjectId", nullable = false)
	public Integer getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

}