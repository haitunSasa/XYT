package com.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * HomeworkStudent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "homework_student", catalog = "XYT")
public class HomeworkStudent implements java.io.Serializable {

	// Fields

	private Integer hsId;
	private Integer homeworkId;
	private Integer studentId;
	private Integer isFinishied;
	private Integer finishTime;
	private Integer isChangeTime;
	private Integer changeTime;
	private Integer isSignend;
	private Integer signParent;

	// Constructors

	/** default constructor */
	public HomeworkStudent() {
	}

	/** minimal constructor */
	public HomeworkStudent(Integer homeworkId, Integer studentId) {
		this.homeworkId = homeworkId;
		this.studentId = studentId;
	}

	/** full constructor */
	public HomeworkStudent(Integer homeworkId, Integer studentId,
			Integer isFinishied, Integer finishTime, Integer isChangeTime,
			Integer changeTime, Integer isSignend, Integer signParent) {
		this.homeworkId = homeworkId;
		this.studentId = studentId;
		this.isFinishied = isFinishied;
		this.finishTime = finishTime;
		this.isChangeTime = isChangeTime;
		this.changeTime = changeTime;
		this.isSignend = isSignend;
		this.signParent = signParent;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "hsId", unique = true, nullable = false)
	public Integer getHsId() {
		return this.hsId;
	}

	public void setHsId(Integer hsId) {
		this.hsId = hsId;
	}

	@Column(name = "homeworkId", nullable = false)
	public Integer getHomeworkId() {
		return this.homeworkId;
	}

	public void setHomeworkId(Integer homeworkId) {
		this.homeworkId = homeworkId;
	}

	@Column(name = "studentId", nullable = false)
	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@Column(name = "isFinishied")
	public Integer getIsFinishied() {
		return this.isFinishied;
	}

	public void setIsFinishied(Integer isFinishied) {
		this.isFinishied = isFinishied;
	}

	@Column(name = "FinishTime")
	public Integer getFinishTime() {
		return this.finishTime;
	}

	public void setFinishTime(Integer finishTime) {
		this.finishTime = finishTime;
	}

	@Column(name = "isChangeTime")
	public Integer getIsChangeTime() {
		return this.isChangeTime;
	}

	public void setIsChangeTime(Integer isChangeTime) {
		this.isChangeTime = isChangeTime;
	}

	@Column(name = "ChangeTime")
	public Integer getChangeTime() {
		return this.changeTime;
	}

	public void setChangeTime(Integer changeTime) {
		this.changeTime = changeTime;
	}

	@Column(name = "IsSignend")
	public Integer getIsSignend() {
		return this.isSignend;
	}

	public void setIsSignend(Integer isSignend) {
		this.isSignend = isSignend;
	}

	@Column(name = "signParent")
	public Integer getSignParent() {
		return this.signParent;
	}

	public void setSignParent(Integer signParent) {
		this.signParent = signParent;
	}

}