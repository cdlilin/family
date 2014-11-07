package org.swz.com.family.entity;

import java.util.Date;

public class FamilyIncludApply {
	private String applyId;
	private String applyUserId;
	private Date applyTime;
	private String applyTimeStr;
	private String familyName;
	private String applyFamilyId;
	private String applyValidate;
	private String applyIncludeFamilyId;
	private int isDone;
	private int applyResult;
	private String applyUserName;
	
	
	public String getApplyFamilyId() {
		return applyFamilyId;
	}
	public void setApplyFamilyId(String applyFamilyId) {
		this.applyFamilyId = applyFamilyId;
	}
	public String getApplyIncludeFamilyId() {
		return applyIncludeFamilyId;
	}
	public void setApplyIncludeFamilyId(String applyIncludeFamilyId) {
		this.applyIncludeFamilyId = applyIncludeFamilyId;
	}
	public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	 
	public String getApplyUserId() {
		return applyUserId;
	}
	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getApplyTimeStr() {
		return applyTimeStr;
	}
	public void setApplyTimeStr(String applyTimeStr) {
		this.applyTimeStr = applyTimeStr;
	}
	 
	public String getApplyValidate() {
		return applyValidate;
	}
	public void setApplyValidate(String applyValidate) {
		this.applyValidate = applyValidate;
	}
	 
	public int getIsDone() {
		return isDone;
	}
	public void setIsDone(int isDone) {
		this.isDone = isDone;
	}
	public int getApplyResult() {
		return applyResult;
	}
	public void setApplyResult(int applyResult) {
		this.applyResult = applyResult;
	}
 
	

}
