package com.ActionListener.bookCheck;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Frame.BookOrderCheckFrame;
import com.Frame.MainFrame;

public class bookCheck implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		BookOrderCheckFrame.setBookOrderCheckFrameIsNull();
		MainFrame.addIFrame(BookOrderCheckFrame.getBookOrderCheckFrame());
	}

}
