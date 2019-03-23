package com.ActionListener.readerModi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Frame.BookModiFrame;
import com.Frame.MainFrame;
import com.Frame.ReaderModiFrame;

public class readerModi implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ReaderModiFrame.setReaderModiFrameIsNull();
		MainFrame.addIFrame(ReaderModiFrame.getReaderModiFrame());
	}

}
