package com.suixin.vy.model.details.y;

import java.io.Serializable;
import java.util.List;

import com.suixin.vy.model.details.CommentList;

public class Data implements Serializable{	private static final long serialVersionUID = 792462036L;	private List<CommentList> commentList;	private String orderStr;
	public List<CommentList> getCommentList() {		return this.commentList;	}
	public void setCommentList(List<CommentList> commentList) {		this.commentList = commentList;	}
	public String getOrderStr() {		return this.orderStr;	}
	public void setOrderStr(String orderStr) {		this.orderStr = orderStr;	}
	public Data() {}
	public Data(List<CommentList> commentList, String orderStr){
		super();		this.commentList = commentList;		this.orderStr = orderStr;
	}
	public String toString() {
		return "Data [commentList = " + commentList + ", orderStr = " + orderStr + "]";	}
}