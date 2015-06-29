package edu.nju.controller.msgqueue.operation;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.model.service.ChessBoardModelService;

public class DoubleClickOperation extends MineOperation implements ClickOperation {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	protected int x;
	protected int y;
	public DoubleClickOperation(int x ,int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ChessBoardModelService chess = OperationQueue.getChessBoardModel();
		chess.quickExcavate(x, y);
	}

}
