package com.hhit.entity;

/**
 * ATeachingAccess entity. @author MyEclipse Persistence Tools
 */

public class ATeachingAccess implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ATerm ATerm;
	private Student Student;
	private Teacher Teacher;
	private ATeachingAccount ATeachingAccount;
	private Double grade;

	// Constructors

	/** default constructor */
	public ATeachingAccess() {
	}

	/** full constructor */
	public ATeachingAccess(ATerm ATerm, Student Student, Teacher Teacher,
			ATeachingAccount ATeachingAccount, Double grade) {
		this.ATerm = ATerm;
		this.Student = Student;
		this.Teacher = Teacher;
		this.ATeachingAccount = ATeachingAccount;
		this.grade = grade;
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

	public Student getStudent() {
		return this.Student;
	}

	public void setStudent(Student Student) {
		this.Student =Student;
	}

	public Teacher getTeacher() {
		return this.Teacher;
	}

	public void setTeacher(Teacher Teacher) {
		this.Teacher = Teacher;
	}

	public ATeachingAccount getATeachingAccount() {
		return this.ATeachingAccount;
	}

	public void setATeachingAccount(ATeachingAccount ATeachingAccount) {
		this.ATeachingAccount = ATeachingAccount;
	}

	public Double getGrade() {
		return this.grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

}