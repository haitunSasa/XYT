package com.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StudentInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student_Info", catalog = "XYT")
public class StudentInfo implements java.io.Serializable {

	// Fields

	private Integer studentId;
	private String studentName;
	private String idNumber;
	private Integer classId;

	// Constructors

	/** default constructor */
	public StudentInfo() {
	}

	/** full constructor */
	public StudentInfo(String studentName, String idNumber, Integer classId) {
		this.studentName = studentName;
		this.idNumber = idNumber;
		this.classId = classId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "studentId", unique = true, nullable = false)
	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@Column(name = "studentName")
	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Column(name = "idNumber")
	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	@Column(name = "classId")
	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

}