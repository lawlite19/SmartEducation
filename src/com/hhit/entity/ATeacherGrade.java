package com.hhit.entity;

/**
 * ATeacherGrade entity. @author MyEclipse Persistence Tools
 */

public class ATeacherGrade implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	private ATerm ATerm;
	private Teacher Teacher;
	
	private Double selfgrade;
	private Double stugrade;
	private Double teachgrade;
	private Double peergrade;
	private Double allgrade;


	// Constructors

	/** default constructor */
	public ATeacherGrade() {
	}

	/** full constructor */
	public ATeacherGrade(ATerm ATerm, Teacher Teacher, Double selfgrade,
			Double stugrade, Double teachgrade, Double peergrade,Double allgrade) {
		this.ATerm = ATerm;
		this.Teacher = Teacher;
		this.selfgrade = selfgrade;
		this.stugrade = stugrade;
		this.teachgrade = teachgrade;
		this.peergrade = peergrade;
		this.allgrade=allgrade;
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

	public Teacher getTeacher() {
		return this.Teacher;
	}

	public void setTeacher(Teacher Teacher) {
		this.Teacher = Teacher;
	}

	public Double getSelfgrade() {
		return this.selfgrade;
	}

	public void setSelfgrade(Double selfgrade) {
		this.selfgrade = selfgrade;
	}

	public Double getStugrade() {
		return this.stugrade;
	}

	public void setStugrade(Double stugrade) {
		this.stugrade = stugrade;
	}

	public Double getTeachgrade() {
		return this.teachgrade;
	}

	public void setTeachgrade(Double teachgrade) {
		this.teachgrade = teachgrade;
	}

	public Double getPeergrade() {
		return this.peergrade;
	}

	public void setPeergrade(Double peergrade) {
		this.peergrade = peergrade;
	}
	public Double getAllgrade() {
		return allgrade;
	}

	public void setAllgrade(Double allgrade) {
		this.allgrade = allgrade;
	}

}