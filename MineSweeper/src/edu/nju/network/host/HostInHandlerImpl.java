package edu.nju.network.host;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.msgqueue.operation.MineOperation;
import edu.nju.controller.msgqueue.operation.SetMineOperation;


public class HostInHandlerImpl implements HostInHandler{
	/**
	 * 主服务器端，得到MineOperaion，并把它加入到OperationQueue.
	 */
	@Override
	public void inputHandle(Object data) {
		// TODO Auto-generated method stub
		MineOperation op = (MineOperation) data;
		op.setBelongToHost(false);
		OperationQueue.addMineOperation(op);
		System.out.println("成功接收到了操作！");
		if(op instanceof SetMineOperation){
			System.out.println("接收到了设置雷操作！");
			
		}
		
	}
}
