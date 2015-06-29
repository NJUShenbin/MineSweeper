package edu.nju.controller.msgqueue.operation;

import java.util.ArrayList;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.model.impl.ChessBoardModelImpl;

public class SetMineOperation extends MineOperation{

	int width;
	int height;
	int mineNum;
	int[] mineIndexs;
	int clickMine;
	public SetMineOperation(int width,int height,int mineNum,int clickedMine) {
		// TODO Auto-generated constructor stub
		this.width = width;;
		this.height = height;
		this.mineNum = mineNum;
		mineIndexs = new int[mineNum];
		this.clickMine = clickedMine;
		
		//随机选雷
		ArrayList<Integer> blocks = new ArrayList<Integer>();
		int size = width*height;
		if(mineNum>=size){
			mineNum = size-1;
		}
		for(int i=0;i<size;i++){
			blocks.add(i);			
		}
	
		for(int i=0;i<mineNum;i++){
			int ramdom = (int)(Math.random()*blocks.size());
			//假如随机的雷和已点击的类相同，跳过
			if(blocks.get(ramdom)==clickedMine){
				i--;
				continue;
			}
			
			mineIndexs[i] = blocks.get(ramdom);
			blocks.remove(ramdom);			
		}
		
	}
	@Override
	//布雷操作不可分割，否则可能导致多次布雷。
	public  synchronized void execute() {
		// TODO Auto-generated method stub
		ChessBoardModelImpl chessBoard = (ChessBoardModelImpl) OperationQueue.getChessBoardModel();
		if(chessBoard.hasSetMine()){
			return;
		}
		//如果是来自服务器的布雷请求
		if(belongToHost){
			//布雷
			new Thread(){
				public void run() {
					chessBoard.setBlock(mineIndexs);
				}
			}.start();
			
		}
	}

	
}
