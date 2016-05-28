package com.hhit.entity;

/**
 * AStudentAccessAccount entity. @author MyEclipse Persistence Tools
 */

public class AStudentAccessAccount implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ATerm ATerm;
	private String description;
	private Integer point;

	// Constructors

	/** default constructor */
	public AStudentAccessAccount() {
	}

	/** full constructor */
	public AStudentAccessAccount(ATerm ATerm, String description, Integer point) {
		this.ATerm = ATerm;
		this.description = description;
		this.point = point;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ATerm getATerm() {
		return this.ATerm;
	}

	public void setATerm(ATerm ATerm) {
		this.ATerm = ATerm;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPoint() {
		return this.point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

}