package com.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JOptionPane;

import com.Frame.MainFrame;
import com.Frame.OnloadFrame;
import com.Vaildate.AccountCheck;

public class onloadButtonAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub         
          String username=OnloadFrame.usernamefield.getText();
          String password=new String(OnloadFrame.passwordfield.getPassword());
          try {
			login(username,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void login(String username,String password) throws Exception{
		if(!AccountCheck.IsEmptyOrNull(username) && !AccountCheck.IsEmptyOrNull(password)){
      	  if(AccountCheck.Check(username, password)){
      		  MainFrame bookframe=new MainFrame(username,password);       		  
      		  OnloadFrame.getOnloadFrame().setVisible(false);
      	  }else{
      		  JOptionPane.showMessageDialog(null, "用户名或者密码不正确╮(╯▽╰)╭", "警告", JOptionPane.ERROR_MESSAGE);
      	  }
        }else{
      	  
      	  JOptionPane.showMessageDialog(null, "请输入用户名和密码！╮(╯▽╰)╭", "警告", JOptionPane.ERROR_MESSAGE);
      	  OnloadFrame.usernamefield.setText("");
      	  OnloadFrame.passwordfield.setText("");
        }
		
	}

}
