package com.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ParentStudent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "parent_student", catalog = "XYT")
public class ParentStudent implements java.io.Serializable {

	// Fields

	private Integer psId;
	private Integer parentId;
	private Integer studentId;

	// Constructors

	/** default constructor */
	public ParentStudent() {
	}

	/** full constructor */
	public ParentStudent(Integer parentId, Integer studentId) {
		this.parentId = parentId;
		this.studentId = studentId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "psId", unique = true, nullable = false)
	public Integer getPsId() {
		return this.psId;
	}

	public void setPsId(Integer psId) {
		this.psId = psId;
	}

	@Column(name = "parentId", nullable = false)
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "studentId", nullable = false)
	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

}