package com.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CreditInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "credit_info", catalog = "XYT")
public class CreditInfo implements java.io.Serializable {

	// Fields

	private Integer idNumber;
	private Integer credit;

	// Constructors

	/** default constructor */
	public CreditInfo() {
	}

	/** minimal constructor */
	public CreditInfo(Integer idNumber) {
		this.idNumber = idNumber;
	}

	/** full constructor */
	public CreditInfo(Integer idNumber, Integer credit) {
		this.idNumber = idNumber;
		this.credit = credit;
	}

	// Property accessors
	@Id
	@Column(name = "idNumber", unique = true, nullable = false)
	public Integer getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(Integer idNumber) {
		this.idNumber = idNumber;
	}

	@Column(name = "credit")
	public Integer getCredit() {
		return this.credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

}