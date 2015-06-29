package edu.nju.model.impl;

import java.io.Serializable;

public class GameLevel implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private String name;
	private int width;
	private int height;
	private int mineNum;
	
	public GameLevel(String name, int width, int height, int mineNum) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		this.mineNum = mineNum;
	}
	
	public GameLevel(String name) {
		super();
		this.name = name;
		switch (name) {
		case "大":
			width = 30;
			height = 16;
			mineNum = 99;
			break;
		case "中":
			width = 16;
			height = 16;
			mineNum = 40;
			break;
		case "小":
			width = 9;
			height = 9;
			mineNum = 10;
			break;
		default:
			break;
		}
		
		

	}


	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMineNum() {
		return mineNum;
	}

	public void setMineNum(int mineNum) {
		this.mineNum = mineNum;
	}
	
}
