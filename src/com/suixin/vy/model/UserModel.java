package com.suixin.vy.model;

import android.graphics.Bitmap;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

public class UserModel extends BmobObject {
	private Long name;// 用户名，手机号
	private String pass;// 用户密码
	private String nick;// 昵称
	private Integer point;// 积分
	private String sex;// 性别
	private BmobDate birthday;// 生日
	private String declaration;// 旅行宣言
	private Bitmap head;// 头像
	private Integer attention;// 关注个数
	private Integer fans;// 粉丝个数

	/** 绑定setTableName("表名") */
	public UserModel() {
		//this.setTableName("user");
	}

	public Long getName() {
		return name;
	}

	public void setName(Long name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public BmobDate getBirthday() {
		return birthday;
	}

	public void setBirthday(BmobDate birthday) {
		this.birthday = birthday;
	}

	public String getDeclaration() {
		return declaration;
	}

	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}

	public Bitmap getHead() {
		return head;
	}

	public void setHead(Bitmap head) {
		this.head = head;
	}

	public Integer getAttention() {
		return attention;
	}

	public void setAttention(Integer attention) {
		this.attention = attention;
	}

	public Integer getFans() {
		return fans;
	}

	public void setFans(Integer fans) {
		this.fans = fans;
	}
	
	
	@Override
	public String toString() {
		return "UserModel [name=" + name + ", pass=" + pass + ", nick=" + nick
				+ ", point=" + point + ", sex=" + sex + ", birthday="
				+ birthday + ", declaration=" + declaration + ", head=" + head
				+ ", attention=" + attention + ", fans=" + fans + "]";
	}
	

}
