package edu.nju.network.modelProxy;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.msgqueue.operation.LeftClickOperation;
import edu.nju.controller.msgqueue.operation.MineOperation;
import edu.nju.controller.service.GameControllerService;
import edu.nju.network.client.ClientService;

public class GameControllerProxy extends ModelProxy implements GameControllerService{

	public GameControllerProxy(ClientService client) {
		// TODO Auto-generated constructor stub
		this.net = client;
	}
	
	@Override
	public boolean handleLeftClick(int x, int y) {
		// TODO Auto-generated method stub
		MineOperation op = new LeftClickOperation(x,y);
		op.setBelongToHost(false);		
		net.submitOperation(op);
		System.out.println("In GameControllerProxy :has send op!");
		
		return true;
	}

	@Override
	public boolean handleRightClick(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleDoubleClick(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
