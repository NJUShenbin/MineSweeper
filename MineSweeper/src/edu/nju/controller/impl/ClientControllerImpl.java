package edu.nju.controller.impl;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.msgqueue.operation.SetClientOperation;
import edu.nju.controller.msgqueue.operation.SetHostOperation;
import edu.nju.controller.service.ClientControllerService;

public class ClientControllerImpl implements ClientControllerService{

	
	public boolean setupClient(String ip){
		OperationQueue.addMineOperation(new SetClientOperation());
		return true;
	}

}
