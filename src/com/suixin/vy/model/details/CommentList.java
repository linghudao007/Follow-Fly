package com.suixin.vy.model.details;

import java.io.Serializable;
import java.util.List;

public class CommentList implements Serializable{	private static final long serialVersionUID = 517870835L;	private String createdTime;	private long commentId;	private String content;	private String guid;	private long replyToId;	private User user;	private String title;
	public String getCreatedTime() {		return this.createdTime;	}
	public void setCreatedTime(String createdTime) {		this.createdTime = createdTime;	}
	public long getCommentId() {		return this.commentId;	}
	public void setCommentId(long commentId) {		this.commentId = commentId;	}
	public String getContent() {		return this.content;	}
	public void setContent(String content) {		this.content = content;	}
	public String getGuid() {		return this.guid;	}
	public void setGuid(String guid) {		this.guid = guid;	}
	public long getReplyToId() {		return this.replyToId;	}
	public void setReplyToId(long replyToId) {		this.replyToId = replyToId;	}
	public User getUser() {		return this.user;	}
	public void setUser(User user) {		this.user = user;	}
	public String getTitle() {		return this.title;	}
	public void setTitle(String title) {		this.title = title;	}
	public CommentList() {}
	public CommentList(String createdTime, long commentId, String content, String guid, long replyToId, User user, String title){
		super();		this.createdTime = createdTime;		this.commentId = commentId;		this.content = content;		this.guid = guid;		this.replyToId = replyToId;		this.user = user;		this.title = title;
	}
	public String toString() {
		return "CommentList [createdTime = " + createdTime + ", commentId = " + commentId + ", content = " + content + ", guid = " + guid + ", replyToId = " + replyToId + ", user = " + user + ", title = " + title + "]";	}
}