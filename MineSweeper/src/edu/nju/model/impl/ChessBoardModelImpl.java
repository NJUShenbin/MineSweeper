package edu.nju.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.msgqueue.operation.SetMineOperation;
import edu.nju.model.po.BlockPO;
import edu.nju.model.service.ChessBoardModelService;
import edu.nju.model.service.GameModelService;
import edu.nju.model.service.ParameterModelService;
import edu.nju.model.state.BlockState;
import edu.nju.model.state.GameResultState;
import edu.nju.model.state.GameState;
import edu.nju.model.vo.BlockVO;

public class ChessBoardModelImpl extends BaseModel implements ChessBoardModelService{
	
	private GameModelService gameModel;
	private ParameterModelImpl parameterModel;
	private int width;
	private int height;
	
	private BlockPO[][] blockMatrix;
	HashMap<Integer, BlockPO> map;
	private boolean isHost = true;
	
	//因为第一次点击才布雷，增添判断
	private boolean hasClicked = false;

	//只能布雷一次
	private boolean hasSetMine = false;
	
	public ChessBoardModelImpl(ParameterModelService parameterModel){
		this.parameterModel = (ParameterModelImpl) parameterModel;
	}

	public boolean hasSetMine() {
		return hasSetMine;		
	}
	public void setHasSetMine() {
				hasSetMine = true;
	}
	
	@Override
	public boolean initialize(int width, int height, int mineNum) {
		// TODO Auto-generated method stub
		/********************简单示例初始化方法，待完善********************/
		this.width = width;
		this.height = height;
		
		blockMatrix = new BlockPO[width][height];
		int index = 0;
		hasClicked = false;
		hasSetMine = false;
		
		map = new HashMap<Integer, BlockPO>();
	
		for(int i = 0 ; i<width; i++){
			for (int j = 0 ; j< height; j++){
				//把数组中元素顺序添加到map中，方便一会随机选取
				blockMatrix[i][j] = new BlockPO(i,j);
				map.put(index, blockMatrix[i][j]);			
				index++;
			}
		}
		
		this.parameterModel.setMineNum(mineNum);
		/***********请在删除上述内容的情况下，完成自己的内容****************/
		
		this.printBlockMatrix();
		
		return false;
	}

	@Override
	public boolean excavate(int x, int y) {
		// TODO Auto-generated method stub
		/********************简单示例挖开方法，待完善********************/
		if(blockMatrix == null)
			return false;
		
		//假如之前没有被点击过，且是主机（联机模式下），进行布雷。这样做是为了让第一次踩不到雷
		if(!hasClicked&&isHost){
			//算出被点击雷的序号，让布雷操作跳过它
			int blockKey = x*blockMatrix[0].length+y;
			hasClicked = true;
			OperationQueue.addMineOperation(new SetMineOperation(width, height, parameterModel.getmineNum(),blockKey));
			Thread.yield();
		}
		
		//thread block until chessboard set mine
			synchronized (this) {
				while(!hasSetMine){
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}				
			}
			
		
		
		
	
		List<BlockPO> blockList = new ArrayList<BlockPO>();
		BlockPO block = blockMatrix[x][y];
		
		if(block.getState().equals(BlockState.FLAG)){
			return false;
		}
		
		block.setState(BlockState.CLICK);
		blockList.add(block);
		
		GameState gameState = GameState.RUN;
		if(block.isMine()){
			gameState = GameState.OVER;
			this.gameModel.gameOver(GameResultState.FAIL);
		}
		
		//通知观察者！
		super.updateChange(new UpdateMessage("excute",this.getDisplayList(blockList, gameState)));			
		
		
	
		if(block.getMineNum()==0){
			int upBound = y-1;
			int bottomBound = y+1;
			int leftBound = x-1;
			int rightBound = x+1;
			//防止过界
			if(upBound<0){
				upBound = 0;
			}
			if(bottomBound==height){
				bottomBound = height-1;
			}
			if(leftBound<0){
				leftBound=0;
			}
			if(rightBound==width){
				rightBound=width-1;
			}
			
			for(int i=leftBound;i<=rightBound;i++){
				for(int j = upBound;j<=bottomBound;j++){
					//假如已经被点过，那就跳过
					BlockState state = blockMatrix[i][j].getState();
					if(state.equals(BlockState.CLICK)){
						continue;
					}else{
						//递归
						excavate(i, j);
					}
				}
			}
			
			
		}
		
		return true;
	}
	
	@Override
	public boolean mark(int x, int y) {
		// TODO Auto-generated method stub
		if(blockMatrix == null)
			return false;
		
		List<BlockPO> blocks = new ArrayList<BlockPO>();
		BlockPO block = blockMatrix[x][y];
		 
		BlockState state = block.getState();
		if(state == BlockState.UNCLICK){
			block.setState(BlockState.FLAG);
			this.parameterModel.minusMineNum();
		}
		else if(state == BlockState.FLAG){
			block.setState(BlockState.UNCLICK);
			this.parameterModel.addMineNum();
		}
		
		blocks.add(block);	
		super.updateChange(new UpdateMessage("excute",this.getDisplayList(blocks, GameState.RUN)));
		
		return true;
	}

	@Override
	public boolean quickExcavate(int x, int y) {
		// TODO Auto-generated method stub
		
		int upBound = y-1;
		int bottomBound = y+1;
		int leftBound = x-1;
		int rightBound = x+1;
		//防止过界
		if(upBound<0){
			upBound = 0;
		}
		if(bottomBound==height){
			bottomBound = height-1;
		}
		if(leftBound<0){
			leftBound=0;
		}
		if(rightBound==width){
			rightBound=width-1;
		}
		
		int flagNum = 0;
		
		for(int i=leftBound;i<=rightBound;i++){
			for(int j = upBound;j<=bottomBound;j++){
				//caculate surrounding flag number
				BlockState state = blockMatrix[i][j].getState();
				if(state.equals(BlockState.FLAG)){
					flagNum++;
				}
			}
		}
		System.out.println(flagNum+"is flag number");		
		
		if(flagNum==blockMatrix[x][y].getMineNum()){
			
			
			for(int i=leftBound;i<=rightBound;i++){
				for(int j = upBound;j<=bottomBound;j++){
					//假如已经被点过，那就跳过
					BlockState state = blockMatrix[i][j].getState();
					if(state.equals(BlockState.FLAG)){
						continue;
					}else{
						//递归
						excavate(i, j);
					}
				}
			}
			
			
		}
		
		return false;
	}

	/**
	 * 示例方法，可选择是否保留该形式
	 * 
	 * 初始化BlockMatrix中的Block，并随机设置mineNum颗雷
	 * 同时可以为每个Block设定附近的雷数
	 * @param mineNum
	 * @return
	 */
	public boolean setBlock(int[] mineIndex){
		
		
		//获得一个雷表
		for(int i=0;i<mineIndex.length;i++){
			BlockPO minePO = map.get(mineIndex[i]);
			minePO.setMine(true);
			addMineNum(minePO.getX(), minePO.getY());			
		}
		printBlockMatrix();		
		setHasSetMine();	
		
		synchronized (this) {
			notify();
		}		
		return false;
	}
	
	
	/**
	 * 示例方法，可选择是否保留该形式
	 * 
	 * 将(i,j)位置附近的Block雷数加1
	 * @param i
	 * @param j
	 */
	private void addMineNum(int i, int j){
		int width = blockMatrix.length;
		int height = blockMatrix[0].length;
		
		int tempI = i-1;		
		
		for(;tempI<=i+1;tempI++){
			int tempJ = j-1;
			for(;tempJ<=j+1;tempJ++){
				if((tempI>-1&&tempI<width)&&(tempJ>-1&&tempJ<height)){
//					System.out.println(i+";"+j+":"+tempI+";"+tempJ+":");
					blockMatrix[tempI][tempJ].addMine();
				}
			}
		}
		
	}
	
	/**
	 * 将逻辑对象转化为显示对象
	 * @param blocks
	 * @param gameState
	 * @return
	 */
	private List<BlockVO> getDisplayList(List<BlockPO> blocks, GameState gameState){
		List<BlockVO> result = new ArrayList<BlockVO>();
		for(BlockPO block : blocks){
			if(block != null){
				BlockVO displayBlock = block.getDisplayBlock(gameState);
				if(displayBlock.getState() != null)
				result.add(displayBlock);
			}
		}
		return result;
	}

	@Override
	public void setGameModel(GameModelService gameModel) {
		// TODO Auto-generated method stub
		this.gameModel = gameModel;
	}
	
	private void printBlockMatrix(){
		for(BlockPO[] blocks : this.blockMatrix){
			for(BlockPO b :blocks){
				String p = b.getMineNum()+"";
				if(b.isMine())
					p="*";
				System.out.print(p);
			}
			System.out.println();
		}
	}

	public boolean isHost() {
		return isHost;
	}

	public void setHost(boolean isHost) {
		this.isHost = isHost;
	}
}
