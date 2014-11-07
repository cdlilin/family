package org.swz.com.family.entity;

import java.util.Date;

public class MergeApply {
	
	private String mergeApplyId;
	private String applyFamilyId;
	private String mergeFamilyId;
	private int applyStatus;
	private int applyResult;
	private Date applyTime;
	private String applyUserId;
	private String mergePersonId;
	private String applyFamilyName;
	private String applyUserName;
	private int mergeType ;
	
	private Person mergePerson;
	
	public String getMergeApplyId() {
		return mergeApplyId;
	}
	public void setMergeApplyId(String mergeApplyId) {
		this.mergeApplyId = mergeApplyId;
	}
	public String getApplyFamilyId() {
		return applyFamilyId;
	}
	public void setApplyFamilyId(String applyFamilyId) {
		this.applyFamilyId = applyFamilyId;
	}
	 
	public String getMergeFamilyId() {
		return mergeFamilyId;
	}
	public void setMergeFamilyId(String mergeFamilyId) {
		this.mergeFamilyId = mergeFamilyId;
	}
	 
	public String getApplyFamilyName() {
		return applyFamilyName;
	}
	public void setApplyFamilyName(String applyFamilyName) {
		this.applyFamilyName = applyFamilyName;
	}
	public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	public Person getMergePerson() {
		return mergePerson;
	}
	public void setMergePerson(Person mergePerson) {
		this.mergePerson = mergePerson;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
 
	 
	public int getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(int applyStatus) {
		this.applyStatus = applyStatus;
	}
	public int getApplyResult() {
		return applyResult;
	}
	public void setApplyResult(int applyResult) {
		this.applyResult = applyResult;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public String getApplyUserId() {
		return applyUserId;
	}
	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}
	public String getMergePersonId() {
		return mergePersonId;
	}
	public void setMergePersonId(String mergePersonId) {
		this.mergePersonId = mergePersonId;
	}
	public int getMergeType() {
		return mergeType;
	}
	public void setMergeType(int mergeType) {
		this.mergeType = mergeType;
	}
	 
	
	
	

}
