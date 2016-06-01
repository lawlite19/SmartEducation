package com.hhit.entity;

/**
 * ASelfAccessAccount entity. @author MyEclipse Persistence Tools
 */

public class ASelfAccessAccount implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ATerm ATerm;
	private double allPoint;
	private String description;
	private String descriptionA;
	private double APoint;
	private String descriptionB;
	private double BPoint;
	private String descriptionC;
	private double CPoint;
	private String descriptionD;
	private double DPoint;

	// Constructors

	/** default constructor */
	public ASelfAccessAccount() {
	}

	/** full constructor */
	public ASelfAccessAccount(ATerm ATerm, double allPoint, String description,
			String descriptionA, double APoint, String descriptionB,
			double BPoint, String descriptionC, double CPoint,
			String descriptionD, double DPoint) {
		this.ATerm = ATerm;
		this.allPoint = allPoint;
		this.description = description;
		this.descriptionA = descriptionA;
		this.APoint = APoint;
		this.descriptionB = descriptionB;
		this.BPoint = BPoint;
		this.descriptionC = descriptionC;
		this.CPoint = CPoint;
		this.descriptionD = descriptionD;
		this.DPoint = DPoint;
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

	public double getAllPoint() {
		return this.allPoint;
	}

	public void setAllPoint(double allPoint) {
		this.allPoint = allPoint;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionA() {
		return this.descriptionA;
	}

	public void setDescriptionA(String descriptionA) {
		this.descriptionA = descriptionA;
	}

	public double getAPoint() {
		return this.APoint;
	}

	public void setAPoint(double APoint) {
		this.APoint = APoint;
	}

	public String getDescriptionB() {
		return this.descriptionB;
	}

	public void setDescriptionB(String descriptionB) {
		this.descriptionB = descriptionB;
	}

	public double getBPoint() {
		return this.BPoint;
	}

	public void setBPoint(double BPoint) {
		this.BPoint = BPoint;
	}

	public String getDescriptionC() {
		return this.descriptionC;
	}

	public void setDescriptionC(String descriptionC) {
		this.descriptionC = descriptionC;
	}

	public double getCPoint() {
		return this.CPoint;
	}

	public void setCPoint(double CPoint) {
		this.CPoint = CPoint;
	}

	public String getDescriptionD() {
		return this.descriptionD;
	}

	public void setDescriptionD(String descriptionD) {
		this.descriptionD = descriptionD;
	}

	public double getDPoint() {
		return this.DPoint;
	}

	public void setDPoint(double DPoint) {
		this.DPoint = DPoint;
	}

}