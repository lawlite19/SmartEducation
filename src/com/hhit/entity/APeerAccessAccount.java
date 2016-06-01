package com.hhit.entity;

/**
 * APeerAccessAccount entity. @author MyEclipse Persistence Tools
 */

public class APeerAccessAccount implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ATerm ATerm;
	private String description;
	private Integer points;

	// Constructors

	/** default constructor */
	public APeerAccessAccount() {
	}

	/** minimal constructor */
	public APeerAccessAccount(ATerm ATerm) {
		this.ATerm = ATerm;
	}

	/** full constructor */
	public APeerAccessAccount(ATerm ATerm, String description, Integer points) {
		this.ATerm = ATerm;
		this.description = description;
		this.points = points;
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

	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

}