package com.Entity;

import java.util.Date;

/**
 * Created by tan on 2017/1/24.
 */
public class HHS {
    private Integer hsId;
    private Integer homeworkId;
    private Integer studentId; //学生id
    private Integer isFinishied;
    private Integer finishTime;
    private Integer isChangeTime;
    private Integer changeTime;
    private Integer isSignend;
    private Integer signParent;
    private Integer subjectId;
    private String title;
    private String content;
    private Date assignTime;
    private Integer plantime;
    private Integer classId;
    private Integer teacherId;

    public HHS() {
    }

    public HHS(Integer hsId, Integer homeworkId, Integer studentId, Integer isFinishied, Integer finishTime, Integer isChangeTime, Integer changeTime, Integer isSignend, Integer signParent, Integer subjectId, String title, String content, Date assignTime, Integer plantime, Integer classId, Integer teacherId) {
        this.hsId = hsId;
        this.homeworkId = homeworkId;
        this.studentId = studentId;
        this.isFinishied = isFinishied;
        this.finishTime = finishTime;
        this.isChangeTime = isChangeTime;
        this.changeTime = changeTime;
        this.isSignend = isSignend;
        this.signParent = signParent;
        this.subjectId = subjectId;
        this.title = title;
        this.content = content;
        this.assignTime = assignTime;
        this.plantime = plantime;
        this.classId = classId;
        this.teacherId = teacherId;
    }

    public Integer getHsId() {
        return hsId;
    }

    public void setHsId(Integer hsId) {
        this.hsId = hsId;
    }

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getIsFinishied() {
        return isFinishied;
    }

    public void setIsFinishied(Integer isFinishied) {
        this.isFinishied = isFinishied;
    }

    public Integer getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Integer finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getIsChangeTime() {
        return isChangeTime;
    }

    public void setIsChangeTime(Integer isChangeTime) {
        this.isChangeTime = isChangeTime;
    }

    public Integer getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Integer changeTime) {
        this.changeTime = changeTime;
    }

    public Integer getIsSignend() {
        return isSignend;
    }

    public void setIsSignend(Integer isSignend) {
        this.isSignend = isSignend;
    }

    public Integer getSignParent() {
        return signParent;
    }

    public void setSignParent(Integer signParent) {
        this.signParent = signParent;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Date assignTime) {
        this.assignTime = assignTime;
    }

    public Integer getPlantime() {
        return plantime;
    }

    public void setPlantime(Integer plantime) {
        this.plantime = plantime;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
