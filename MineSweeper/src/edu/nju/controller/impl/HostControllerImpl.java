package edu.nju.controller.impl;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.msgqueue.operation.SetClientOperation;
import edu.nju.controller.msgqueue.operation.SetHostOperation;
import edu.nju.controller.service.HostControllerService;

public class HostControllerImpl implements HostControllerService{
	public boolean serviceetupHost(){
		OperationQueue.addMineOperation(new SetHostOperation());
		return true;

	}

}
