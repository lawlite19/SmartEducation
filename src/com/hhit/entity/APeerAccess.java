package com.hhit.entity;

/**
 * APeerAccess entity. @author MyEclipse Persistence Tools
 */

public class APeerAccess implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ATerm ATerm;
	private Teacher leader;
	private Teacher teacher;
	private Double peerAccess;

	// Constructors

	/** default constructor */
	public APeerAccess() {
	}

	/** full constructor */
	public APeerAccess(ATerm ATerm, Teacher leader,
			Teacher teacher, Double peerAccess) {
		this.ATerm = ATerm;
		this.leader = leader;
		this.teacher = teacher;
		this.peerAccess = peerAccess;
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

	public Double getPeerAccess() {
		return this.peerAccess;
	}

	public void setPeerAccess(Double peerAccess) {
		this.peerAccess = peerAccess;
	}

	public Teacher getLeader() {
		return leader;
	}

	public void setLeader(Teacher leader) {
		this.leader = leader;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}