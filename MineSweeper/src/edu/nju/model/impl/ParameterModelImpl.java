package edu.nju.model.impl;

import edu.nju.model.service.ParameterModelService;

public class ParameterModelImpl extends BaseModel implements ParameterModelService{
	
	private int maxMine;
	private int mineNum;
	private int flagNum = 0;

	@Override
	public boolean setMineNum(int num) {
		// TODO Auto-generated method stub
		mineNum = num;
		maxMine = num;
		flagNum =0;
		super.updateChange(new UpdateMessage("mineNum", mineNum));
		return true;
	}

	@Override
	public boolean addMineNum() {
		// TODO Auto-generated method stub
		mineNum++;
		flagNum--;
		if(mineNum>maxMine){
			mineNum--;
			return false;
		}
		
		super.updateChange(new UpdateMessage("mineNum", mineNum));
		return true;
	}

	@Override
	public boolean minusMineNum() {
		// TODO Auto-generated method stub
		mineNum--;
		flagNum++;
		if(mineNum<0){
			mineNum++;
			return false;
		}
		
		super.updateChange(new UpdateMessage("mineNum", mineNum));
		return true;
	}

	public int getmineNum(){
		return mineNum;
	}
}
