package org.swz.com.family.entity;

import java.util.Date;
import java.util.List;

import org.swz.com.family.common.util.CustomDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Talk {
	
	private String talkId ;
	private String talkContent;
	private String userId ;
	private String nick ;
	private Date createTime;
	private Date modifiedTime;
	private int replyCount ;  
	private String repliedTalkId ;  
	private int agreeCount;   
	private int talkType;
	private int talkFocus;
	private int isAgree;
	private int sex;
	private String headUrl;
	private List<Talk> replies; 
	
	private List<FamilyTalkAgree> agrees;
	
	
	public int getTalkFocus() {
		return talkFocus;
	}
	public void setTalkFocus(int talkFocus) {
		this.talkFocus = talkFocus;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public List<FamilyTalkAgree> getAgrees() {
		return agrees;
	}
	public void setAgrees(List<FamilyTalkAgree> agrees) {
		this.agrees = agrees;
	}
	public int getIsAgree() {
		return isAgree;
	}
	public void setIsAgree(int isAgree) {
		this.isAgree = isAgree;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public List<Talk> getReplies() {
		return replies;
	}
	public void setReplies(List<Talk> replies) {
		this.replies = replies;
	}
	public String getTalkId() {
		return talkId;
	}
	public void setTalkId(String talkId) {
		this.talkId = talkId;
	}
	public String getTalkContent() {
		return talkContent;
	}
	public void setTalkContent(String talkContent) {
		this.talkContent = talkContent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class) 
	public Date getCreateTime() {
		return createTime;
	} 
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@JsonSerialize(using = CustomDateSerializer.class) 
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public String getRepliedTalkId() {
		return repliedTalkId;
	}
	public void setRepliedTalkId(String repliedTalkId) {
		this.repliedTalkId = repliedTalkId;
	} 
	public int getAgreeCount() {
		return agreeCount;
	} 
	public void setAgreeCount(int agreeCount) {
		this.agreeCount = agreeCount;
	}
	public int getTalkType() {
		return talkType;
	}
	public void setTalkType(int talkType) {
		this.talkType = talkType;
	} 
}
