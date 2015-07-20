/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ganesh Sharma
 */
/*******************************************************************************
*/

//import the packages for using the classes in them into the program

import java.awt.EventQueue;
import java.awt.Frame;
import javax.swing.JDialog;

public class Main implements Runnable {
	final Frame frame;
	public Main(Frame frame) {
		this.frame = frame;
	}
	public void run() {
		frame.setVisible(true);
	}
public static void main(String[] args) {
		JDialog.setDefaultLookAndFeelDecorated(true);
		// Throw a nice little title page up on the screen first
		new Splash().showSplash(3000);
		//EventQueue.invokeLater(new Main(new JLibrary()));
        EventQueue.invokeLater(new Main(new LoginScreen()));
	}
}
