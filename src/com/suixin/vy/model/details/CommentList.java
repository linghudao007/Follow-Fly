package com.suixin.vy.model.details;

import java.io.Serializable;
import java.util.List;


public class CommentList implements Serializable{	private static final long serialVersionUID = 225340782L;	private String planGuid;	private long commentId;	private String content;	private String createdTime;	private long score;	private String orderStr;	private long replyToId;	private User user;	private String title;	private long type;
	public String getPlanGuid() {		return this.planGuid;	}
	public void setPlanGuid(String planGuid) {		this.planGuid = planGuid;	}
	public long getCommentId() {		return this.commentId;	}
	public void setCommentId(long commentId) {		this.commentId = commentId;	}
	public String getContent() {		return this.content;	}
	public void setContent(String content) {		this.content = content;	}
	public String getCreatedTime() {		return this.createdTime;	}
	public void setCreatedTime(String createdTime) {		this.createdTime = createdTime;	}
	public long getScore() {		return this.score;	}
	public void setScore(long score) {		this.score = score;	}
	public String getOrderStr() {		return this.orderStr;	}
	public void setOrderStr(String orderStr) {		this.orderStr = orderStr;	}
	public long getReplyToId() {		return this.replyToId;	}
	public void setReplyToId(long replyToId) {		this.replyToId = replyToId;	}
	public User getUser() {		return this.user;	}
	public void setUser(User user) {		this.user = user;	}
	public String getTitle() {		return this.title;	}
	public void setTitle(String title) {		this.title = title;	}
	public long getType() {		return this.type;	}
	public void setType(long type) {		this.type = type;	}
	public CommentList() {}
	public CommentList(String planGuid, long commentId, String content, String createdTime, long score, String orderStr, long replyToId, User user, String title, long type){
		super();		this.planGuid = planGuid;		this.commentId = commentId;		this.content = content;		this.createdTime = createdTime;		this.score = score;		this.orderStr = orderStr;		this.replyToId = replyToId;		this.user = user;		this.title = title;		this.type = type;
	}
	public String toString() {
		return "CommentList [planGuid = " + planGuid + ", commentId = " + commentId + ", content = " + content + ", createdTime = " + createdTime + ", score = " + score + ", orderStr = " + orderStr + ", replyToId = " + replyToId + ", user = " + user + ", title = " + title + ", type = " + type + "]";	}
}