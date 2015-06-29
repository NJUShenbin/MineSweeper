package edu.nju.controller.msgqueue.operation;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.main.JMineSweeper;
import edu.nju.model.impl.ChessBoardModelImpl;
import edu.nju.model.impl.GameModelImpl;
import edu.nju.network.client.ClientInHandlerImpl;
import edu.nju.network.client.ClientServiceImpl;
import edu.nju.network.modelProxy.GameControllerProxy;
import edu.nju.network.modelProxy.GameModelProxy;
import edu.nju.network.modelProxy.MenuControllerProxy;

public class SetClientOperation extends MineOperation{

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ClientServiceImpl client = new ClientServiceImpl();		
		ClientInHandlerImpl clientH = new ClientInHandlerImpl();
		
		client.init("127.0.0.1", clientH);
	//	GameModelProxy gameProxy = new GameModelProxy(client);
		
		clientH.addObserver(JMineSweeper.operationQueue);

		   GameModelImpl gameModel = (GameModelImpl) OperationQueue.getGameModel();
		   gameModel.setHost(false);
		   ChessBoardModelImpl chessBoardModel = (ChessBoardModelImpl)OperationQueue.getChessBoardModel();
		   chessBoardModel.setHost(false);		   
		
		
		//把监听器里的controller换成代理的，这样信息将会发出去。
		GameControllerProxy gameControllerProxy = new GameControllerProxy(client);
		JMineSweeper.ui.getCoreListener().setMouseController(gameControllerProxy);
		MenuControllerProxy menuControllerProxy = new MenuControllerProxy(client);
		JMineSweeper.ui.getMenuListener().setMenuController(menuControllerProxy);


	//	gameProxy.startGame();
		
		
		
	}
	
	
}
