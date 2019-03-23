package com.ActionListener.bookModi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Frame.BookModiFrame;
import com.Frame.MainFrame;

public class bookModi implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		BookModiFrame.setBookModiFrameIsNull();
		MainFrame.addIFrame(BookModiFrame.getBookModiFrame());
	}

}
