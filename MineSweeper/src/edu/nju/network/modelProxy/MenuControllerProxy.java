package edu.nju.network.modelProxy;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.msgqueue.operation.SetGameLevelOperation;
import edu.nju.controller.msgqueue.operation.StartGameOperation;
import edu.nju.controller.service.MenuControllerService;
import edu.nju.model.impl.GameLevel;
import edu.nju.network.client.ClientService;
import edu.nju.view.SetCustomFrame;

public class MenuControllerProxy extends ModelProxy implements MenuControllerService{
	SetCustomFrame frame;

	
	public MenuControllerProxy(ClientService client) {
		// TODO Auto-generated constructor stub
		this.net = client;
	}
	
	public boolean startGame() {
		// TODO Auto-generated method stub
		net.submitOperation(new StartGameOperation());
		return true;
	}

	public boolean easyGame() {
		net.submitOperation(new SetGameLevelOperation(new GameLevel("小")));		
		return true;
	}
	public boolean hardGame() {
		net.submitOperation(new SetGameLevelOperation(new GameLevel("中")));		
		return true;
	}
	
	public boolean hellGame() {
		net.submitOperation(new SetGameLevelOperation(new GameLevel("大")));		
		return true;
	}

	@Override
	public boolean customGame() {
		// TODO Auto-generated method stub
		frame = new SetCustomFrame();	
		new Thread(new WaitInput()).start();		
		return true;
	}

	
	class WaitInput implements Runnable {
		public void run() {
			while(true){
				if(frame.isSure){
					net.submitOperation(frame.getOp());
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
