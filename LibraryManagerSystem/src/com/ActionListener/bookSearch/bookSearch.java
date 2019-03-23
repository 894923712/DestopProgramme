package com.ActionListener.bookSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Frame.BookSearchFrame;
import com.Frame.MainFrame;

public class bookSearch implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		BookSearchFrame.setBookSearchFrameIsNull();
		MainFrame.addIFrame(BookSearchFrame.getBookSearchFrame());
	}

}
