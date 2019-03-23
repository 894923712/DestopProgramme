package com.ActionListener.readerAdd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Frame.MainFrame;
import com.Frame.ReaderAddFrame;

public class readerAdd implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ReaderAddFrame.setReaderAddFrameIsNull();
		MainFrame.addIFrame(ReaderAddFrame.getReaderAddFrame());
	}
}
