package com.suixin.vz.bean;

import java.util.Date;

import com.suixin.vy.core.MyApplication;

public class ChatMessage
{
	private String name;
	private String msg;
	private Type type;
	private Date date;
	private String headPath;

	public String getHeadPath() {
        return headPath;
    }



    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }
    public enum Type
	{
		INCOMING, OUTCOMING
	}
	
	public ChatMessage()
	{
	}
	
	

	public ChatMessage(String msg, Type type, Date date)
	{
		super();
		this.msg = msg;
		this.type = type;
		this.date = date;
		if(MyApplication.headPath !=null)
		    setHeadPath(MyApplication.headPath);
	}



	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}

}
