package com.ActionListener.bookTypeModi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Frame.BookModiFrame;
import com.Frame.BookTypeModiFrame;
import com.Frame.MainFrame;

public class bookTypeModi implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		BookTypeModiFrame.setBookTypeModiFrameIsNull();
		MainFrame.addIFrame(BookTypeModiFrame.getBookTypeModiFrame());
	}

}
