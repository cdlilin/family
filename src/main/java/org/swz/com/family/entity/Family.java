package org.swz.com.family.entity;

import java.util.Date;

public class Family {
	
	private String familyId;
	private String familyName;
	private String createUserId;
	private Date createTime;
	private int includeFlag;
	private int userType;
	private int areaId;
	private int isFamilyAdmin;
	private String familyRules;
	private String surname;
	
	//用于匹配家庭的时候使用
	private Person person;
	
	
	//查询同姓家谱
	private int familyCount;
	private String createUserName;
	private String areaName;
	private String createUserHeadUrl;
	private int createUserSex;
	
	
	
	public String getCreateUserHeadUrl() {
		return createUserHeadUrl;
	}
	public void setCreateUserHeadUrl(String createUserHeadUrl) {
		this.createUserHeadUrl = createUserHeadUrl;
	}
	 
	public int getCreateUserSex() {
		return createUserSex;
	}
	public void setCreateUserSex(int createUserSex) {
		this.createUserSex = createUserSex;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public int getFamilyCount() {
		return familyCount;
	}
	public void setFamilyCount(int familyCount) {
		this.familyCount = familyCount;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getFamilyRules() {
		return familyRules;
	}
	public void setFamilyRules(String familyRules) {
		this.familyRules = familyRules;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public int getIsFamilyAdmin() {
		return isFamilyAdmin;
	}
	public void setIsFamilyAdmin(int isFamilyAdmin) {
		this.isFamilyAdmin = isFamilyAdmin;
	}
	 
	public String getFamilyId() {
		return familyId;
	}
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getIncludeFlag() {
		return includeFlag;
	}
	public void setIncludeFlag(int includeFlag) {
		this.includeFlag = includeFlag;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	
	

}
