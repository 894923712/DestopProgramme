package com.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class exitEvent implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
         System.exit(1);
	}

}
