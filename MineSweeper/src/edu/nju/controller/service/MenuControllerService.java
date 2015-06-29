package edu.nju.controller.service;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.msgqueue.operation.SetClientOperation;
import edu.nju.controller.msgqueue.operation.SetGameLevelOperation;
import edu.nju.controller.msgqueue.operation.SetHostOperation;
import edu.nju.model.impl.GameLevel;

public interface MenuControllerService {
	/**
	 * 开始游戏
	 * @return
	 */
	public boolean startGame();

	public boolean easyGame();
	public boolean hardGame();
	
	public boolean hellGame();
	public boolean customGame();
}
