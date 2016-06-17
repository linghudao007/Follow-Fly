package com.suixin.vy.model.rank;

import java.io.Serializable;
import java.util.List;

public class UserEvaluations implements Serializable{	private static final long serialVersionUID = 885008771L;	private String nick;	private String createdTime;	private String evaluationUuid;	private List<String> tags;	private String content;	private String avatarUrl;	private String updatedTime;	private long score;	private long type;	private long evaluationId;	public String getNick() {		return this.nick;	}
	public void setNick(String nick) {		this.nick = nick;	}
	public String getCreatedTime() {		return this.createdTime;	}
	public void setCreatedTime(String createdTime) {		this.createdTime = createdTime;	}
	public String getEvaluationUuid() {		return this.evaluationUuid;	}
	public void setEvaluationUuid(String evaluationUuid) {		this.evaluationUuid = evaluationUuid;	}
	public List<String> getTags() {		return this.tags;	}
	public void setTags(List<String> tags) {		this.tags = tags;	}
	public String getContent() {		return this.content;	}
	public void setContent(String content) {		this.content = content;	}
	public String getAvatarUrl() {		return this.avatarUrl;	}
	public void setAvatarUrl(String avatarUrl) {		this.avatarUrl = avatarUrl;	}
	public String getUpdatedTime() {		return this.updatedTime;	}
	public void setUpdatedTime(String updatedTime) {		this.updatedTime = updatedTime;	}
	public long getScore() {		return this.score;	}
	public void setScore(long score) {		this.score = score;	}
	public long getType() {		return this.type;	}
	public void setType(long type) {		this.type = type;	}
	public long getEvaluationId() {		return this.evaluationId;	}
	public void setEvaluationId(long evaluationId) {		this.evaluationId = evaluationId;	}
	public UserEvaluations() {}
	public UserEvaluations(String nick, String createdTime, String evaluationUuid, List<String> tags, String content, String avatarUrl, String updatedTime, long score, long type, long evaluationId){
		super();		this.nick = nick;		this.createdTime = createdTime;		this.evaluationUuid = evaluationUuid;		this.tags = tags;		this.content = content;		this.avatarUrl = avatarUrl;		this.updatedTime = updatedTime;		this.score = score;		this.type = type;		this.evaluationId = evaluationId;	}
	public String toString() {
		return "UserEvaluations [nick = " + nick + ", createdTime = " + createdTime + ", evaluationUuid = " + evaluationUuid + ", tags = " + tags + ", content = " + content + ", avatarUrl = " + avatarUrl + ", updatedTime = " + updatedTime + ", score = " + score + ", type = " + type + ", evaluationId = " + evaluationId + ", tags = " + tags + "]";	}
}