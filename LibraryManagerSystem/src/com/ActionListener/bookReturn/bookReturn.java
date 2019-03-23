package com.ActionListener.bookReturn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Frame.BookReturnFrame;
import com.Frame.MainFrame;

public class bookReturn implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		BookReturnFrame.setBookReturnFrameIsNull();
		MainFrame.addIFrame(BookReturnFrame.getBookReturnFrame());
	}

}
