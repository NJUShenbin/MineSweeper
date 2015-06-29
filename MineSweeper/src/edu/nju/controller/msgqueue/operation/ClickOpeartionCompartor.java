package edu.nju.controller.msgqueue.operation;

public class ClickOpeartionCompartor {

	static public boolean isEqualOperation(MineOperation op1,MineOperation op2){
		
		if((op1 instanceof ClickOperation)&&(op2 instanceof ClickOperation)){
			ClickOperation one = (ClickOperation)op1;
			ClickOperation other = (ClickOperation)op2;
			
			if((one.getX()==other.getX())&&(one.getY()==other.getY())){
				return true;
			}
			
		}
		return false;
	}
}
