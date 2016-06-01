package com.hhit.entity;

/**
 * ATatics entity. @author MyEclipse Persistence Tools
 */

public class ATatics implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ATerm ATerm;
	private Double peerProportion;
	private Double selfProportion;
	private Double studentProportion;
	private Double teachingProportion;

	// Constructors

	/** default constructor */
	public ATatics() {
	}

	/** full constructor */
	public ATatics(ATerm ATerm, Double peerProportion, Double selfProportion,
			Double studentProportion, Double teachingProportion) {
		this.ATerm = ATerm;
		this.peerProportion = peerProportion;
		this.selfProportion = selfProportion;
		this.studentProportion = studentProportion;
		this.teachingProportion = teachingProportion;
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

	public Double getPeerProportion() {
		return this.peerProportion;
	}

	public void setPeerProportion(Double peerProportion) {
		this.peerProportion = peerProportion;
	}

	public Double getSelfProportion() {
		return this.selfProportion;
	}

	public void setSelfProportion(Double selfProportion) {
		this.selfProportion = selfProportion;
	}

	public Double getStudentProportion() {
		return this.studentProportion;
	}

	public void setStudentProportion(Double studentProportion) {
		this.studentProportion = studentProportion;
	}

	public Double getTeachingProportion() {
		return this.teachingProportion;
	}

	public void setTeachingProportion(Double teachingProportion) {
		this.teachingProportion = teachingProportion;
	}

}