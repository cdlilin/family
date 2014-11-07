package org.swz.com.family.entity;

public class FamilyForest {
	
	private String familyId;
	private String parentId;
	private String contactPersonId;
	private int isChecked;
	private String rootId;
	
	private String currentFamilyName;
	private String parentFamilyName;
	
	
	
	public String getCurrentFamilyName() {
		return currentFamilyName;
	}
	public void setCurrentFamilyName(String currentFamilyName) {
		this.currentFamilyName = currentFamilyName;
	}
	public String getParentFamilyName() {
		return parentFamilyName;
	}
	public void setParentFamilyName(String parentFamilyName) {
		this.parentFamilyName = parentFamilyName;
	}
	public String getFamilyId() {
		return familyId;
	}
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getContactPersonId() {
		return contactPersonId;
	}
	public void setContactPersonId(String contactPersonId) {
		this.contactPersonId = contactPersonId;
	}
	public int getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(int isChecked) {
		this.isChecked = isChecked;
	}
	public String getRootId() {
		return rootId;
	}
	public void setRootId(String rootId) {
		this.rootId = rootId;
	} 
}
