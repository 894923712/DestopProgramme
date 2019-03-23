package com.ActionListener.bookadd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Frame.BookAddFrame;
import com.Frame.MainFrame;

public class bookAdd implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		BookAddFrame.setBookAddFrameIsNull();
		MainFrame.addIFrame(BookAddFrame.getBookAddFrame());
	}

}
