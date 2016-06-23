package com.suixin.vz.find.model.odd;

import java.io.Serializable;
import java.util.List;

public class TourPicList implements Serializable{	private static final long serialVersionUID = 536109864L;	private String likeNum;	private String commentNum;	private String createdTime;	private String distance;	private String weight;	private List<Object> likedList;	private String shareUrl;	private List<Object> commentList;	private String coverWidth;	private String coverHeight;	private List<String> photoUrls;	private String type;	private String shareTitle;	private List<Object> companyList;	private List<String> thumbPhotoUrls;	private String placeName;	private CreaterUser createrUser;	private String description;	private String isLike;	private String guid;	private String isNewOrHot;	private String forwardNum;
	public String getLikeNum() {		return this.likeNum;	}
	public void setLikeNum(String likeNum) {		this.likeNum = likeNum;	}
	public String getCommentNum() {		return this.commentNum;	}
	public void setCommentNum(String commentNum) {		this.commentNum = commentNum;	}
	public String getCreatedTime() {		return this.createdTime;	}
	public void setCreatedTime(String createdTime) {		this.createdTime = createdTime;	}
	public String getDistance() {		return this.distance;	}
	public void setDistance(String distance) {		this.distance = distance;	}
	public String getWeight() {		return this.weight;	}
	public void setWeight(String weight) {		this.weight = weight;	}
	public List<Object> getLikedList() {		return this.likedList;	}
	public void setLikedList(List<Object> likedList) {		this.likedList = likedList;	}
	public String getShareUrl() {		return this.shareUrl;	}
	public void setShareUrl(String shareUrl) {		this.shareUrl = shareUrl;	}
	public List<Object> getCommentList() {		return this.commentList;	}
	public void setCommentList(List<Object> commentList) {		this.commentList = commentList;	}
	public String getCoverWidth() {		return this.coverWidth;	}
	public void setCoverWidth(String coverWidth) {		this.coverWidth = coverWidth;	}
	public String getCoverHeight() {		return this.coverHeight;	}
	public void setCoverHeight(String coverHeight) {		this.coverHeight = coverHeight;	}
	public List<String> getPhotoUrls() {		return this.photoUrls;	}
	public void setPhotoUrls(List<String> photoUrls) {		this.photoUrls = photoUrls;	}
	public String getType() {		return this.type;	}
	public void setType(String type) {		this.type = type;	}
	public String getShareTitle() {		return this.shareTitle;	}
	public void setShareTitle(String shareTitle) {		this.shareTitle = shareTitle;	}
	public List<Object> getCompanyList() {		return this.companyList;	}
	public void setCompanyList(List<Object> companyList) {		this.companyList = companyList;	}
	public List<String> getThumbPhotoUrls() {		return this.thumbPhotoUrls;	}
	public void setThumbPhotoUrls(List<String> thumbPhotoUrls) {		this.thumbPhotoUrls = thumbPhotoUrls;	}
	public String getPlaceName() {		return this.placeName;	}
	public void setPlaceName(String placeName) {		this.placeName = placeName;	}
	public CreaterUser getCreaterUser() {		return this.createrUser;	}
	public void setCreaterUser(CreaterUser createrUser) {		this.createrUser = createrUser;	}
	public String getDescription() {		return this.description;	}
	public void setDescription(String description) {		this.description = description;	}
	public String getIsLike() {		return this.isLike;	}
	public void setIsLike(String isLike) {		this.isLike = isLike;	}
	public String getGuid() {		return this.guid;	}
	public void setGuid(String guid) {		this.guid = guid;	}
	public String getIsNewOrHot() {		return this.isNewOrHot;	}
	public void setIsNewOrHot(String isNewOrHot) {		this.isNewOrHot = isNewOrHot;	}
	public String getForwardNum() {		return this.forwardNum;	}
	public void setForwardNum(String forwardNum) {		this.forwardNum = forwardNum;	}
	public TourPicList() {}
	public TourPicList(String likeNum, String commentNum, String createdTime, String distance, String weight, List<Object> likedList, String shareUrl, List<Object> commentList, String coverWidth, String coverHeight, List<String> photoUrls, String type, String shareTitle, List<Object> companyList, List<String> thumbPhotoUrls, String placeName, CreaterUser createrUser, String description, String isLike, String guid, String isNewOrHot, String forwardNum){
		super();		this.likeNum = likeNum;		this.commentNum = commentNum;		this.createdTime = createdTime;		this.distance = distance;		this.weight = weight;		this.likedList = likedList;		this.shareUrl = shareUrl;		this.commentList = commentList;		this.coverWidth = coverWidth;		this.coverHeight = coverHeight;		this.photoUrls = photoUrls;		this.type = type;		this.shareTitle = shareTitle;		this.companyList = companyList;		this.thumbPhotoUrls = thumbPhotoUrls;		this.placeName = placeName;		this.createrUser = createrUser;		this.description = description;		this.isLike = isLike;		this.guid = guid;		this.isNewOrHot = isNewOrHot;		this.forwardNum = forwardNum;
	}
	public String toString() {
		return "TourPicList [likeNum = " + likeNum + ", commentNum = " + commentNum + ", createdTime = " + createdTime + ", distance = " + distance + ", weight = " + weight + ", likedList = " + likedList + ", shareUrl = " + shareUrl + ", commentList = " + commentList + ", coverWidth = " + coverWidth + ", coverHeight = " + coverHeight + ", photoUrls = " + photoUrls + ", type = " + type + ", shareTitle = " + shareTitle + ", companyList = " + companyList + ", thumbPhotoUrls = " + thumbPhotoUrls + ", placeName = " + placeName + ", createrUser = " + createrUser + ", description = " + description + ", isLike = " + isLike + ", guid = " + guid + ", isNewOrHot = " + isNewOrHot + ", forwardNum = " + forwardNum + "]";	}
}