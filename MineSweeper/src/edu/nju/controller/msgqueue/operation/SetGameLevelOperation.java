package edu.nju.controller.msgqueue.operation;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.model.impl.GameLevel;
import edu.nju.model.impl.GameModelImpl;
import edu.nju.model.service.GameModelService;

public class SetGameLevelOperation extends MineOperation{
	GameLevel level;
	public SetGameLevelOperation(GameLevel level){
		this.level = level;
	}
	
	public void execute() {
		GameModelImpl game = (GameModelImpl)OperationQueue.getGameModel();
		game.setGameLevel(level);
	}
}
