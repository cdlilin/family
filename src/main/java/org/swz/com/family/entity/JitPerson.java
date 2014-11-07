package org.swz.com.family.entity;

import java.util.Date;
import java.util.List;

import org.swz.com.family.common.util.DateUtils;

public class JitPerson {  
	private String personId;
	private String firstName;
	private String lastName;
	private String fullName;
	private String nick;
	private int nationality;
	private int profession;
	private int addressId;
	private String addressName;
	private String birthday;
	private String birthDayStr; 
	private int constellation;
	private int status;
	private String cid;
	private String modifyUserId;
	private String createUserId;
	private Date createTime;
	private Date modifyTime;
	private String headUrl; 
	private String email;
	private String phone;
	private int sex;
	
	private int isHFlag;
	
	private String profile;
	
	private String userName;
	
	private String familyId;
	
	private int relationShipType;
	
	private String parentId; 
	
	
	private List<JitPerson> spouses;  
	
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public int getIsHFlag() {
		return isHFlag;
	}
	public void setIsHFlag(int isHFlag) {
		this.isHFlag = isHFlag;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<JitPerson> getSpouses() {
		return spouses;
	}
	public void setSpouses(List<JitPerson> spouses) {
		this.spouses = spouses;
	}
	public int getRelationShipType() {
		return relationShipType;
	}
	public void setRelationShipType(int relationShipType) {
		this.relationShipType = relationShipType;
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
 
	public String getBirthDayStr() {
		return birthDayStr;
	}
	public void setBirthDayStr(String birthDayStr) {
		this.birthDayStr = birthDayStr;
	}
	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	 
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	 
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	 
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	 
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public int getNationality() {
		return nationality;
	}
	public void setNationality(int nationality) {
		this.nationality = nationality;
	}
	public int getProfession() {
		return profession;
	}
	public void setProfession(int profession) {
		this.profession = profession;
	}
	 
	 
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public int getConstellation() {
		return constellation;
	}
	public void setConstellation(int constellation) {
		this.constellation = constellation;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	 
	 
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
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
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	} 
}
