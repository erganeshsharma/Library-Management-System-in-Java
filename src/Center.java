/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ganesh Sharma
 */
//import the packages for using the classes in them into this class

import java.awt.*;

/**
 *A PUBLIC CLASS FOR CENTER.JAVA
 */
public class Center {
	JLibrary l; //for using the class in JLibrary.java

	public Center(JLibrary l) {
		this.l = l;
	}

	public void LibraryCenter() {
		//for centering the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		l.setLocation((screenSize.width - l.getWidth()) / 2, (screenSize.height - l.getHeight()) / 2);
	}
}