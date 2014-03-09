package com.example.whatsapp;

public class InstantMessage {
	
	public static final int ME = 0;
	public static final int OTHER = 1;

	int who ;
	String money;
	
	public InstantMessage(int who , String money)
	{
		this.who=who;
		this.money=money;
	}
	

}
