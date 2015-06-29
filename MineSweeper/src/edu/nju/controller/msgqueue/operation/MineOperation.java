package edu.nju.controller.msgqueue.operation;

import java.io.Serializable;

import edu.nju.model.impl.UpdateMessage;
import edu.nju.network.TransformObject;

public abstract class MineOperation implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected boolean belongToHost = true;
	
	public abstract void execute();

	public boolean isBelongToHost() {
		return belongToHost;
	}

	public void setBelongToHost(boolean belongToHost) {
		this.belongToHost = belongToHost;
	}
}
