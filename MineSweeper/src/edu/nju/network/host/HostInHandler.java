package edu.nju.network.host;
import edu.nju.controller.msgqueue.operation.*;

public interface HostInHandler {
	/**
	 * 主服务器端，得到MineOperaion，并把它加入到OperationQueue.
	 */
	public void inputHandle(Object data);
		
		
		
	
}
