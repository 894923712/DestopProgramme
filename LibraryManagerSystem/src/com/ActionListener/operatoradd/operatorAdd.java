package com.ActionListener.operatoradd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.Frame.MainFrame;
import com.Frame.OperatorAddFrame;

public class operatorAdd implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		OperatorAddFrame.setOperatorAddFrameIsNull();
		MainFrame.addIFrame(OperatorAddFrame.getOperatorAddFrame());
	}

}
