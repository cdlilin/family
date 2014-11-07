package org.swz.com.family.entity;

import java.util.Date;

import org.swz.com.family.common.util.CustomDateYYMMDDSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class FamilyRule {
	
	private String ruleId;
	private String ruleTitle;
	private String ruleContent;
	private Date createTime;
	private String familyId;
	private int agreeCount;
	private String createUserId;
	private String createUserName;
	
	
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleTitle() {
		return ruleTitle;
	}
	public void setRuleTitle(String ruleTitle) {
		this.ruleTitle = ruleTitle;
	}
	public String getRuleContent() {
		return ruleContent;
	}
	public void setRuleContent(String ruleContent) {
		this.ruleContent = ruleContent;
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
	 
}
