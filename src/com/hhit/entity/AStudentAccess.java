package com.hhit.entity;

/**
 * AStudentAccess entity. @author MyEclipse Persistence Tools
 */

public class AStudentAccess implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Course Course;
	private ATerm ATerm;
	private Student Student;
	private Teacher Teacher;
	private Double studentAccess;

	// Constructors

	/** default constructor */
	public AStudentAccess() {
	}

	/** full constructor */
	public AStudentAccess(Course Course, ATerm ATerm, Student Student,
			Teacher Teacher, Double studentAccess) {
		this.Course = Course;
		this.ATerm = ATerm;
		this.Student = Student;
		this.Teacher = Teacher;
		this.studentAccess = studentAccess;
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

	

	public Double getStudentAccess() {
		return this.studentAccess;
	}

	public void setStudentAccess(Double studentAccess) {
		this.studentAccess = studentAccess;
	}

	public Course getCourse() {
		return Course;
	}

	public void setCourse(Course course) {
		Course = course;
	}

	public Student getStudent() {
		return Student;
	}

	public void setStudent(Student student) {
		Student = student;
	}

	public Teacher getTeacher() {
		return Teacher;
	}

	public void setTeacher(Teacher teacher) {
		Teacher = teacher;
	}

}