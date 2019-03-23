package com.main;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.Frame.BookAddFrame;
import com.Frame.OnloadFrame;

public class Start {
	
	/**
	 * @author LMingX0103380
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //设置系统界面外观
			OnloadFrame.getOnloadFrame();
			
			
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}           
            
            
	}
	

}
