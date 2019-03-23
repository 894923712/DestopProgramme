package com.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Frame.OnloadFrame;

public class resetButtonAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		OnloadFrame.usernamefield.setText("");
		OnloadFrame.passwordfield.setText("");
	}

}
