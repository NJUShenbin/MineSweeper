package edu.nju.controller.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.msgqueue.operation.SetClientOperation;
import edu.nju.controller.msgqueue.operation.SetGameLevelOperation;
import edu.nju.controller.msgqueue.operation.SetHostOperation;
import edu.nju.controller.msgqueue.operation.StartGameOperation;
import edu.nju.controller.service.MenuControllerService;
import edu.nju.model.impl.ChessBoardModelImpl;
import edu.nju.model.impl.GameLevel;
import edu.nju.model.impl.GameModelImpl;
import edu.nju.model.impl.ParameterModelImpl;
import edu.nju.model.impl.StatisticModelImpl;
import edu.nju.network.client.ClientInHandlerImpl;
import edu.nju.network.client.ClientServiceImpl;
import edu.nju.network.host.HostInHandlerImpl;
import edu.nju.network.host.HostServiceImpl;
import edu.nju.network.modelProxy.GameModelProxy;
import edu.nju.view.SetCustomFrame;

public class MenuControllerImpl implements MenuControllerService{

	
	SetCustomFrame frame;
	Thread thread;
	@Override
	public boolean startGame() {
		// TODO Auto-generated method stub
		OperationQueue.addMineOperation(new StartGameOperation());
		return true;
	}

	public boolean easyGame() {
		OperationQueue.addMineOperation(new SetGameLevelOperation(new GameLevel("小")));		
		return true;
	}
	public boolean hardGame() {
		OperationQueue.addMineOperation(new SetGameLevelOperation(new GameLevel("中")));		
		return true;
	}
	
	public boolean hellGame() {
		OperationQueue.addMineOperation(new SetGameLevelOperation(new GameLevel("大")));		
		return true;
	}

	@Override
	public boolean customGame() {
		// TODO Auto-generated method stub
		frame = new SetCustomFrame();	
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaitInput());		

	  
		return true;
	}

	
	class WaitInput implements Runnable {
		int i;
		public void run() {
			
			while(true){
					System.out.print("");
					
				if(frame.isSure){				
					OperationQueue.addMineOperation(frame.getOp());
					System.out.println("检查通过"+(i++));
					break;
				}
				if(frame.isClose){
					break;
				}
				
			}
			
			
			//关闭窗口
			frame.dispose();		
		}
	}

}
