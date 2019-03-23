package com.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Frame.BookAddFrame;

public class closeInterFrameEvent implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		BookAddFrame.getBookAddFrame().doDefaultCloseAction();
		BookAddFrame.setBookAddFrameIsNull();
	}

}
