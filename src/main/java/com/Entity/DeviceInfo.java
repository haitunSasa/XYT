package com.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DeviceInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "device_info", catalog = "XYT")
public class DeviceInfo implements java.io.Serializable {

	// Fields

	private Integer macAddress;
	private Integer idNumber;

	// Constructors

	/** default constructor */
	public DeviceInfo() {
	}

	/** full constructor */
	public DeviceInfo(Integer macAddress, Integer idNumber) {
		this.macAddress = macAddress;
		this.idNumber = idNumber;
	}

	// Property accessors
	@Id
	@Column(name = "macAddress", unique = true, nullable = false)
	public Integer getMacAddress() {
		return this.macAddress;
	}

	public void setMacAddress(Integer macAddress) {
		this.macAddress = macAddress;
	}

	@Column(name = "idNumber", nullable = false)
	public Integer getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(Integer idNumber) {
		this.idNumber = idNumber;
	}

}