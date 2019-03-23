package com.ActionListener.bookType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Frame.BookAddFrame;
import com.Frame.BookTypeAddFrame;
import com.Frame.MainFrame;

public class bookType implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		BookTypeAddFrame.setBookTypeAddFrameIsNull();
		MainFrame.addIFrame(BookTypeAddFrame.getBookTypeAddFrame());
	}

}
