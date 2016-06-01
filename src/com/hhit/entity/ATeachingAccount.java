package com.hhit.entity;

/**
 * ATeachingAccount entity. @author MyEclipse Persistence Tools
 */

public class ATeachingAccount implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ATerm ATerm;
	private DataDict DataDict;
	private String description;
	private Double allPoint;
	private String descriptionA;
	private Double APoint;
	private String descriptionB;
	private Double BPoint;
	private String descriptionC;
	private Double CPoint;
	private String descriptionD;
	private Double DPoint;

	// Constructors

	/** default constructor */
	public ATeachingAccount() {
	}

	/** full constructor */
	public ATeachingAccount(ATerm ATerm, DataDict DataDict,
			String description, Double allPoint, String descriptionA,
			Double APoint, String descriptionB, Double BPoint,
			String descriptionC, Double CPoint, String descriptionD,
			Double DPoint) {
		this.ATerm = ATerm;
		this.DataDict = DataDict;
		this.description = description;
		this.allPoint = allPoint;
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

	public DataDict getDataDict() {
		return this.DataDict;
	}

	public void setDataDict(DataDict DataDict) {
		this.DataDict = DataDict;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAllPoint() {
		return this.allPoint;
	}

	public void setAllPoint(Double allPoint) {
		this.allPoint = allPoint;
	}

	public String getDescriptionA() {
		return this.descriptionA;
	}

	public void setDescriptionA(String descriptionA) {
		this.descriptionA = descriptionA;
	}

	public Double getAPoint() {
		return this.APoint;
	}

	public void setAPoint(Double APoint) {
		this.APoint = APoint;
	}

	public String getDescriptionB() {
		return this.descriptionB;
	}

	public void setDescriptionB(String descriptionB) {
		this.descriptionB = descriptionB;
	}

	public Double getBPoint() {
		return this.BPoint;
	}

	public void setBPoint(Double BPoint) {
		this.BPoint = BPoint;
	}

	public String getDescriptionC() {
		return this.descriptionC;
	}

	public void setDescriptionC(String descriptionC) {
		this.descriptionC = descriptionC;
	}

	public Double getCPoint() {
		return this.CPoint;
	}

	public void setCPoint(Double CPoint) {
		this.CPoint = CPoint;
	}

	public String getDescriptionD() {
		return this.descriptionD;
	}

	public void setDescriptionD(String descriptionD) {
		this.descriptionD = descriptionD;
	}

	public Double getDPoint() {
		return this.DPoint;
	}

	public void setDPoint(Double DPoint) {
		this.DPoint = DPoint;
	}

}