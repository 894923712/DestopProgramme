package com.ActionListener.operatorModi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Frame.MainFrame;
import com.Frame.OperatorAddFrame;
import com.Frame.OperatorModiFrame;

public class operatorModi implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		OperatorModiFrame.setOperatorModiFrameIsNull();
		MainFrame.addIFrame(OperatorModiFrame.getOperatorModiFrame());
	}

}
