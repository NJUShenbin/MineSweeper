package edu.nju.view;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JSlider;

public class SmallChessBoard extends JPanel{

	JSlider widthSlider;
	JSlider heightSlider;
	JSlider mineSlider;
	
	public SmallChessBoard(JSlider widthSlider,JSlider heightSlider,JSlider mineSlider){
		this.widthSlider = widthSlider;
		this.heightSlider = heightSlider;
		this.mineSlider = mineSlider;
	}
	
	public void paintComponent(Graphics g){
		
	}
	
	
}
