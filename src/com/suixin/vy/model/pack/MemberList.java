package com.suixin.vy.model.pack;

import java.io.Serializable;
import java.util.List;

public class MemberList implements Serializable{	private static final long serialVersionUID = 273840575L;	private long fansNum;	private long age;	private String livingPlace;	private long isFavored;	private String avatarThumbUrl;	private String openfireAccount;	private String professional;	private String nick;	private long isIdentify;	private long favorNum;	private long sex;	private String uUID;	private String signature;	private long isTourGuideVerify;	private String telephone;	private long isValid;	private long isCarVerify;
	public long getFansNum() {		return this.fansNum;	}
	public void setFansNum(long fansNum) {		this.fansNum = fansNum;	}
	public long getAge() {		return this.age;	}
	public void setAge(long age) {		this.age = age;	}
	public String getLivingPlace() {		return this.livingPlace;	}
	public void setLivingPlace(String livingPlace) {		this.livingPlace = livingPlace;	}
	public long getIsFavored() {		return this.isFavored;	}
	public void setIsFavored(long isFavored) {		this.isFavored = isFavored;	}
	public String getAvatarThumbUrl() {		return this.avatarThumbUrl;	}
	public void setAvatarThumbUrl(String avatarThumbUrl) {		this.avatarThumbUrl = avatarThumbUrl;	}
	public String getOpenfireAccount() {		return this.openfireAccount;	}
	public void setOpenfireAccount(String openfireAccount) {		this.openfireAccount = openfireAccount;	}
	public String getProfessional() {		return this.professional;	}
	public void setProfessional(String professional) {		this.professional = professional;	}
	public String getNick() {		return this.nick;	}
	public void setNick(String nick) {		this.nick = nick;	}
	public long getIsIdentify() {		return this.isIdentify;	}
	public void setIsIdentify(long isIdentify) {		this.isIdentify = isIdentify;	}
	public long getFavorNum() {		return this.favorNum;	}
	public void setFavorNum(long favorNum) {		this.favorNum = favorNum;	}
	public long getSex() {		return this.sex;	}
	public void setSex(long sex) {		this.sex = sex;	}
	public String getUUID() {		return this.uUID;	}
	public void setUUID(String uUID) {		this.uUID = uUID;	}
	public String getSignature() {		return this.signature;	}
	public void setSignature(String signature) {		this.signature = signature;	}
	public long getIsTourGuideVerify() {		return this.isTourGuideVerify;	}
	public void setIsTourGuideVerify(long isTourGuideVerify) {		this.isTourGuideVerify = isTourGuideVerify;	}
	public String getTelephone() {		return this.telephone;	}
	public void setTelephone(String telephone) {		this.telephone = telephone;	}
	public long getIsValid() {		return this.isValid;	}
	public void setIsValid(long isValid) {		this.isValid = isValid;	}
	public long getIsCarVerify() {		return this.isCarVerify;	}
	public void setIsCarVerify(long isCarVerify) {		this.isCarVerify = isCarVerify;	}
	public MemberList() {}
	public MemberList(long fansNum, long age, String livingPlace, long isFavored, String avatarThumbUrl, String openfireAccount, String professional, String nick, long isIdentify, long favorNum, long sex, String uUID, String signature, long isTourGuideVerify, String telephone, long isValid, long isCarVerify){
		super();		this.fansNum = fansNum;		this.age = age;		this.livingPlace = livingPlace;		this.isFavored = isFavored;		this.avatarThumbUrl = avatarThumbUrl;		this.openfireAccount = openfireAccount;		this.professional = professional;		this.nick = nick;		this.isIdentify = isIdentify;		this.favorNum = favorNum;		this.sex = sex;		this.uUID = uUID;		this.signature = signature;		this.isTourGuideVerify = isTourGuideVerify;		this.telephone = telephone;		this.isValid = isValid;		this.isCarVerify = isCarVerify;
	}
	public String toString() {
		return "MemberList [fansNum = " + fansNum + ", age = " + age + ", livingPlace = " + livingPlace + ", isFavored = " + isFavored + ", avatarThumbUrl = " + avatarThumbUrl + ", openfireAccount = " + openfireAccount + ", professional = " + professional + ", nick = " + nick + ", isIdentify = " + isIdentify + ", favorNum = " + favorNum + ", sex = " + sex + ", uUID = " + uUID + ", signature = " + signature + ", isTourGuideVerify = " + isTourGuideVerify + ", telephone = " + telephone + ", isValid = " + isValid + ", isCarVerify = " + isCarVerify + "]";	}
}