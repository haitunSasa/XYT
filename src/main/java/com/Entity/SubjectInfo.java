package com.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SubjectInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "subject_info", catalog = "XYT")
public class SubjectInfo implements java.io.Serializable {

	// Fields

	private Integer subjectId;
	private String subjectName;

	// Constructors

	/** default constructor */
	public SubjectInfo() {
	}

	/** full constructor */
	public SubjectInfo(String subjectName) {
		this.subjectName = subjectName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "subjectId", unique = true, nullable = false)
	public Integer getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	@Column(name = "subjectName", length = 20)
	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}