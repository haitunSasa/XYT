package com.Entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * ClockInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="clock_info"
    ,catalog="XYT"
)

public class ClockInfo  implements java.io.Serializable {


    // Fields    

     private Integer clockId;
     private Integer studentId;
     private Date clockTime;
     private String clockContent;
     private String clockDay;


    // Constructors

    /** default constructor */
    public ClockInfo() {
    }

	/** minimal constructor */
    public ClockInfo(Integer studentId) {
        this.studentId = studentId;
    }
    
    /** full constructor */
    public ClockInfo(Integer studentId, Date clockTime, String clockContent, String clockDay) {
        this.studentId = studentId;
        this.clockTime = clockTime;
        this.clockContent = clockContent;
        this.clockDay = clockDay;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="clockId", unique=true, nullable=false)

    public Integer getClockId() {
        return this.clockId;
    }
    
    public void setClockId(Integer clockId) {
        this.clockId = clockId;
    }
    
    @Column(name="studentId", nullable=false)

    public Integer getStudentId() {
        return this.studentId;
    }
    
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    
    @Column(name="clockTime", length=8)

    public Date getClockTime() {
        return this.clockTime;
    }
    
    public void setClockTime(Date clockTime) {
        this.clockTime = clockTime;
    }
    
    @Column(name="clockContent", length=200)

    public String getClockContent() {
        return this.clockContent;
    }
    
    public void setClockContent(String clockContent) {
        this.clockContent = clockContent;
    }
    
    @Column(name="clockDay", length=7)

    public String getClockDay() {
        return this.clockDay;
    }
    
    public void setClockDay(String clockDay) {
        this.clockDay = clockDay;
    }
   








}