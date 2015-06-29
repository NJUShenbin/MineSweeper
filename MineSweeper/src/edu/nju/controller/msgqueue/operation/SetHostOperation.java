package edu.nju.controller.msgqueue.operation;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.main.JMineSweeper;
import edu.nju.model.impl.ChessBoardModelImpl;
import edu.nju.model.impl.GameModelImpl;
import edu.nju.model.impl.ParameterModelImpl;
import edu.nju.model.impl.StatisticModelImpl;
import edu.nju.network.host.HostInHandlerImpl;
import edu.nju.network.host.HostServiceImpl;

public class SetHostOperation extends MineOperation{

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		HostServiceImpl host = new HostServiceImpl();
		HostInHandlerImpl hostH = new HostInHandlerImpl();
		//获得chessBoard ，使他的变化能被host知道。
	//	ChessBoardModelImpl chessBoard = (ChessBoardModelImpl) OperationQueue.getChessBoardModel();
	//	GameModelImpl gameModelImpl = (GameModelImpl)OperationQueue.getGameModel();
		
		//让棋盘和游戏model都给host传信息
	//	chessBoard.addObserver(host);
	//	gameModelImpl.addObserver(host);
		System.out.println(JMineSweeper.operationQueue==null);
		
	   JMineSweeper.operationQueue.addObserver(host);	   
		
		if(host.init(hostH)){
			System.out.println("Connecting!");	
			OperationQueue.addMineOperation(new StartGameOperation());
		}
		
	}

}
