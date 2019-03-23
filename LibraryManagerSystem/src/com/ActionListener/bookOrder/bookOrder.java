package com.ActionListener.bookOrder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Frame.BookOrderFrame;
import com.Frame.MainFrame;

public class bookOrder implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		BookOrderFrame.setBookOrderFrameIsNull();
		MainFrame.addIFrame(BookOrderFrame.getBookOrderFrame());
	}

}
