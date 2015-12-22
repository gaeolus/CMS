package org.resl.gs1.cms.gui;

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
public class Slave_GUI extends JFrame implements ActionListener
{
	//Definition of global values and items that are part of the GUI
	int numPressed = 0;
	
	JPanel titlePanel, responsePanel, buttonPanel;
	JLabel responseLabel;
	JButton regButton;
	
	public JPanel createContentPane(){
		//A bottom JPanel to place everything on
		JPanel totalGUI = new JPanel();
		totalGUI.setLayout(null);
		
		//Creation of a JPanel to contain the title label
		titlePanel = new JPanel();
		titlePanel.setLayout(null);
		titlePanel.setLocation(10, 0);
		titlePanel.setSize(500, 30);
		totalGUI.add(titlePanel);
		
		//Creation of a Panel to contain the response label
		responsePanel = new JPanel();
		responsePanel.setLayout(null);
		responsePanel.setLocation(10,40);
		responsePanel.setSize(260,30);
		totalGUI.add(responsePanel);
		
		//Creation of a Panel to contain all the JButtons
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setLocation(10,80);
		buttonPanel.setSize(260,70);
		totalGUI.add(buttonPanel);
		
		//Response Label
		responseLabel = new JLabel("Number of times pressed: " + numPressed);
		responseLabel.setLocation(0, 0);
		responseLabel.setSize(200, 30);
		//responseLabel.setHorizontalAlignment(0);
		responsePanel.add(responseLabel);
		
		//Registration Button
		regButton = new JButton("Registration!");
		regButton.setLocation(0, 0);
		regButton.setSize(120, 30);
		regButton.addActionListener(this);
		buttonPanel.add(regButton);
		
		totalGUI.setOpaque(true);
		return totalGUI;
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == regButton){
			numPressed += 1;
			responseLabel.setText("Number of times pressed: " + numPressed);
		}
	}
	
	private static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("--- Code Management System Prototype ---");
		
		//Create and set up the content pane
		Slave_GUI gui = new Slave_GUI();
		frame.setContentPane(gui.createContentPane());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(280, 190);
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		//Schedule a job for the event-dispatching thread: creating and showing this application's GUI
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
