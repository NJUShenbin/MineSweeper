package edu.nju.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import edu.nju.controller.msgqueue.operation.SetGameLevelOperation;
import edu.nju.model.impl.GameLevel;

import javax.swing.JSlider;

import java.awt.Color;
import java.util.HashMap;

public class SetCustomFrame extends JFrame implements Runnable, ChangeListener {
	// 布雷系数，即最大雷数等于棋盘面积×0.928
	private JPanel contentPane;

	JSlider widthSlider = new JSlider(JSlider.HORIZONTAL, 9, 24, 9);
	JSlider heightSlider = new JSlider(JSlider.HORIZONTAL, 9, 30, 9);
	// 75 is the result of 81*0.927
	JSlider mineNumSlider = new JSlider(JSlider.HORIZONTAL, 10, 75, 10);

	public boolean isSure = false;
	public boolean isClose = false;
	private SetGameLevelOperation op;
	private HashMap<JSlider, JLabel> labelMap = new HashMap<JSlider, JLabel>();

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			this.setVisible(true);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create the frame.
	 */
	public SetCustomFrame() {

		setBounds(100, 100, 755, 401);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 739, 362);
		contentPane.add(panel);
		panel.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 101, 315, 8);
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 209, 315, 8);
		panel.add(separator_1);

		JLabel label = new JLabel("棋盘长");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("宋体", Font.BOLD, 18));
		label.setBounds(10, 45, 62, 26);
		panel.add(label);

		JLabel label_1 = new JLabel("棋盘高");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("宋体", Font.BOLD, 18));
		label_1.setBounds(10, 143, 62, 26);
		panel.add(label_1);

		JLabel label_2 = new JLabel(" 雷数");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("宋体", Font.BOLD, 18));
		label_2.setBounds(0, 242, 62, 26);
		panel.add(label_2);

		mineNumSlider.setBounds(74, 247, 241, 21);
		panel.add(widthSlider);

		heightSlider.setBounds(74, 148, 241, 21);
		panel.add(heightSlider);

		widthSlider.setBounds(74, 45, 241, 21);
		panel.add(mineNumSlider);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 299, 733, 2);
		panel.add(separator_2);

		JButton button_1 = new JButton("确定");
		button_1.setBounds(209, 310, 74, 35);
		panel.add(button_1);

		JButton button_2 = new JButton("取消");
		button_2.setBounds(437, 310, 74, 35);
		panel.add(button_2);

		JLabel widthLabel = new JLabel("9");
		widthLabel.setFont(new Font("宋体", Font.BOLD, 18));
		widthLabel.setForeground(Color.WHITE);
		widthLabel.setBounds(319, 41, 39, 35);
		panel.add(widthLabel);

		JLabel heightLabel = new JLabel("9");
		heightLabel.setForeground(Color.WHITE);
		heightLabel.setFont(new Font("宋体", Font.BOLD, 18));
		heightLabel.setBounds(319, 139, 39, 35);
		panel.add(heightLabel);

		JLabel mineNumLabel = new JLabel("10");
		mineNumLabel.setForeground(Color.WHITE);
		mineNumLabel.setFont(new Font("宋体", Font.BOLD, 18));
		mineNumLabel.setBounds(319, 238, 39, 35);
		panel.add(mineNumLabel);

		this.setVisible(true);

		widthSlider.addChangeListener(this);
		heightSlider.addChangeListener(this);
		mineNumSlider.addChangeListener(this);

		labelMap.put(widthSlider, widthLabel);
		labelMap.put(heightSlider, heightLabel);
		labelMap.put(mineNumSlider, mineNumLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(345, 6, 388, 288);
		panel.add(panel_1);
	}

	public SetGameLevelOperation getOp() {
		return op;
	}

	public void setOp(SetGameLevelOperation op) {
		this.op = op;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// change the relevant number when user slides the slider.
		JSlider slider = (JSlider) e.getSource();
		JLabel label = labelMap.get(slider);
		label.setText(slider.getValue() + "");

		int maxMineNum = (int) (widthSlider.getValue()
				* heightSlider.getValue() * 0.928);
		mineNumSlider.setMaximum(maxMineNum);

	}
}
