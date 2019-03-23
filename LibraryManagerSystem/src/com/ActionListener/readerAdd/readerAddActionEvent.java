package com.ActionListener.readerAdd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.swing.JOptionPane;

import com.Dao.Book_Info_Dao;
import com.Dao.Reader_Info_Dao;
import com.Frame.BookAddFrame;
import com.Frame.ReaderAddFrame;
import com.Vaildate.AccountCheck;

public class readerAddActionEvent implements ActionListener {
	 
	 
	@Override
	public void actionPerformed(ActionEvent agro) {
		// TODO Auto-generated method stub
		 Reader_Info_Dao reader_Info_Dao=new Reader_Info_Dao();
		  ReaderAddFrame readerAddFrame=ReaderAddFrame.getReaderAddFrame();
		  String name=readerAddFrame.namefield.getText().trim();
		  String sex=null;
		  if(readerAddFrame.maleradio.isSelected()){
			  sex="男";
		  }else{
			  sex="女";
		  }	
		  String age=readerAddFrame.agebox.getSelectedItem().toString().trim(); 
		  String job=readerAddFrame.jobfield.getText().trim(); 
		  String zj=readerAddFrame.identityCardTYpebox.getSelectedItem().toString().trim();
		  String identityCard=readerAddFrame.identityCardfield.getText().trim(); 
		  String maxnum=readerAddFrame.maxborrownumfield.getText().trim(); 		  
		  String date=readerAddFrame.Vipdatefield.getText().trim();
		  String tel=readerAddFrame.telfield.getText().trim();
		  String keepmoney=readerAddFrame.keeymoneyfield.getText().trim();
		  String bztime=readerAddFrame.bzdatefield.getText().trim();
		  SimpleDateFormat format=new SimpleDateFormat("YYYYMMDDSS");
		  String readerid=format.format(new Date());
		  try {
	        	 if(AccountCheck.IsEmptyOrNull(name)){
	        		 JOptionPane.showMessageDialog(null, "请读者姓名");       		 
	        	 }else if(AccountCheck.IsEmptyOrNull(sex)){
	        		 JOptionPane.showMessageDialog(null, "请选择读者性别");
	        	 }else if(AccountCheck.IsEmptyOrNull(age)){
	        		 JOptionPane.showMessageDialog(null, "请选择读者年龄");
	        	 }else if(AccountCheck.IsEmptyOrNull(job)){
	        		 JOptionPane.showMessageDialog(null, "请输入职业");
	        	 }else if(AccountCheck.IsEmptyOrNull(zj) || zj.equals("请选择证件类型") ){
	        		 JOptionPane.showMessageDialog(null, "请选择证件类型");
	        	 }else if(AccountCheck.IsEmptyOrNull(identityCard)){
	        		 JOptionPane.showMessageDialog(null, "请输入证件号码");
	        	 }else if(AccountCheck.IsEmptyOrNull(maxnum)){
	        		 JOptionPane.showMessageDialog(null, "请输入最大借书量");
	        	 }else if(AccountCheck.IsEmptyOrNull(date)){
	        		 JOptionPane.showMessageDialog(null, "请输入会员有效证件时间");
	        	 }else if(AccountCheck.IsEmptyOrNull(tel)){
	        		 JOptionPane.showMessageDialog(null, "请输入电话号码");
	        	 }else if(AccountCheck.IsEmptyOrNull(keepmoney)){
	        		 JOptionPane.showMessageDialog(null, "请输入押金");
	        	 }else if(AccountCheck.IsEmptyOrNull(bztime)){
	        		 JOptionPane.showMessageDialog(null, "请输入办证时间");
	        	 }else if(AccountCheck.IsExistReader(readerid)){
	        		 JOptionPane.showMessageDialog(null, "添加失败！╮(╯▽╰)╭, 该读者已存在！");
	        	 }else{	 
	        		 if(reader_Info_Dao.insertReaderInfo(readerid,name,sex,age, job,zj,identityCard,maxnum,date,tel,keepmoney,bztime)){
	    				 JOptionPane.showMessageDialog(null, "添加成功");
	    			 }else{
	    				 JOptionPane.showMessageDialog(null, "添加失败！╮(╯▽╰)╭");
	    			 }
	        	 }
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
	}

}
