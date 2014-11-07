package org.swz.com.family.entity;

import java.util.Date;

import org.swz.com.family.common.util.CustomDateYYMMDDSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class FamilyCulture {
	
	private String cultureId;
	private String cultureTitle;
	private String cultureContent;
	private Date createTime;
	private String familyId;
	private int agreeCount;
	private String createUserId;
	private String createUserName;
	 
	 
	public String getCreateUersName() {
		return createUserName;
	}
	public void setCreateUersName(String createUersName) {
		this.createUserName = createUersName;
	}
	public String getCultureId() {
		return cultureId;
	}
	public void setCultureId(String cultureId) {
		this.cultureId = cultureId;
	}
	public String getCultureTitle() {
		return cultureTitle;
	}
	public void setCultureTitle(String cultureTitle) {
		this.cultureTitle = cultureTitle;
	}
	public String getCultureContent() {
		return cultureContent;
	}
	public void setCultureContent(String cultureContent) {
		this.cultureContent = cultureContent;
	}
	@JsonSerialize(using = CustomDateYYMMDDSerializer.class) 
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFamilyId() {
		return familyId;
	}
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}
	public int getAgreeCount() {
		return agreeCount;
	}
	public void setAgreeCount(int agreeCount) {
		this.agreeCount = agreeCount;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
}
