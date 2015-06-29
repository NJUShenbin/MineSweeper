package edu.nju.network.client;

import java.util.Observable;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.msgqueue.operation.MineOperation;
import edu.nju.controller.msgqueue.operation.SetMineOperation;
import edu.nju.network.TransformObject;

public class ClientInHandlerImpl extends Observable implements ClientInHandler{

	@Override
	public void inputHandle(Object data) {
		// TODO Auto-generated method stub
		//understand data and handle them;
		
		TransformObject obj = (TransformObject) data;
//		MineOperation remoteOperation = (MineOperation)obj.getMsg().getValue();
//		OperationQueue.addMineOperation(remoteOperation);
		this.setChanged();
		MineOperation op = (MineOperation)obj.getMsg().getValue();
		
		if(op instanceof SetMineOperation){
			System.out.println("接收到布雷操作！");
			
		}
		
		this.notifyObservers(op);
		
	}

}
