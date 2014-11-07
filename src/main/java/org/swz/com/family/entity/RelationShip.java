package org.swz.com.family.entity;

public class RelationShip {
	private String familyId;
	private String personId;
	private int relationShipType;
	private int generation;
	private int isFamilyAdmin;
	private String parentId;
	private int familyType;
	
	
	public int getFamilyType() {
		return familyType;
	}
	public void setFamilyType(int familyType) {
		this.familyType = familyType;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getFamilyId() {
		return familyId;
	}
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public int getRelationShipType() {
		return relationShipType;
	}
	public void setRelationShipType(int relationShipType) {
		this.relationShipType = relationShipType;
	}
	public int getGeneration() {
		return generation;
	}
	public void setGeneration(int generation) {
		this.generation = generation;
	}
	public int getIsFamilyAdmin() {
		return isFamilyAdmin;
	}
	public void setIsFamilyAdmin(int isFamilyAdmin) {
		this.isFamilyAdmin = isFamilyAdmin;
	}
	 

}
