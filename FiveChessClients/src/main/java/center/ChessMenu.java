package center;

import author.Author;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author LuoMing luom@3vjia.com
 * 棋盘菜单栏
 * @since 2018-12-06 15:10
 */
public class ChessMenu extends JFrame implements ActionListener {
    public JMenuBar bar;
    public JMenu  select,about;
    public JMenuItem start,exit,computer,back,author;
    public ChessMenu(){
       this.setTitle("五子棋");
       select=new JMenu();
       about=new JMenu();
       bar=new JMenuBar();
       start=new JMenuItem("单人对局");
       exit=new JMenuItem("退出");
       computer=new JMenuItem("网络对战");
       back=new JMenuItem("悔棋");
       author=new JMenuItem("关于作者");
       select.add(start);
       select.add(back);
        select.add(computer);
        select.add(exit);
        about.add(author);
        bar.add(select);
        bar.add(about);
        this.add(bar);

   }
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem=(JMenuItem)e.getSource();
        if(menuItem==exit){
            if(JOptionPane.showConfirmDialog(this,"您真的要退出吗？","温馨提示",JOptionPane.YES_NO_OPTION)==0){
                 System.exit(0);
            }
        }else if(menuItem==back){

        }else if(menuItem==start){

        }else if(menuItem==computer){

        }else if(menuItem==author){
            new Author();
        }
    }
}
