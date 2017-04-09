package com.Entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * HomeworkInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "homework_info", catalog = "XYT")
public class HomeworkInfo implements java.io.Serializable {

	// Fields

	private Integer homeworkId;
	private Integer subjectId;
	private String title;
	private String content;
	private Date assignTime;
	private Integer plantime;
	private Integer classId;
	private Integer teacherId;

	// Constructors

	/** default constructor */
	public HomeworkInfo() {
	}

	/** full constructor */
	public HomeworkInfo(Integer subjectId, String title, String content,
			Date assignTime, Integer plantime, Integer classId,
			Integer teacherId) {
		this.subjectId = subjectId;
		this.title = title;
		this.content = content;
		this.assignTime = assignTime;
		this.plantime = plantime;
		this.classId = classId;
		this.teacherId = teacherId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "homeworkId", unique = true, nullable = false)
	public Integer getHomeworkId() {
		return this.homeworkId;
	}

	public void setHomeworkId(Integer homeworkId) {
		this.homeworkId = homeworkId;
	}

	@Column(name = "subjectId")
	public Integer getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	@Column(name = "title", length = 20)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	@Column(name = "plantime")
	public Integer getPlantime() {
		return this.plantime;
	}

	public void setPlantime(Integer plantime) {
		this.plantime = plantime;
	}

	@Column(name = "classId")
	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	@Column(name = "teacherId")
	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

}