package center;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class MyJTextPane extends JTextPane {
    public void setText(String msg, Font font, Color backgroudcColor,Color forgroundColor) throws BadLocationException {
        SimpleAttributeSet simpleAttributeSet=new SimpleAttributeSet();
        if(backgroudcColor!=null){
            //设置背景色
            StyleConstants.setBackground(simpleAttributeSet,backgroudcColor);
        }
        if(forgroundColor!=null){
            //设置前景色
            StyleConstants.setForeground(simpleAttributeSet,forgroundColor);
        }
        if(font!=null){
            //设置字体
            StyleConstants.setFontFamily(simpleAttributeSet,font.getFontName());
            //设置字体大小
            StyleConstants.setFontSize(simpleAttributeSet,font.getSize());
            if(font.isBold()){
              StyleConstants.setBold(simpleAttributeSet,true);
            }
            if(font.isItalic()){
                StyleConstants.setItalic(simpleAttributeSet,true);
            }

        }
        Document doc=this.getDocument();
        doc.insertString(doc.getLength(),msg,simpleAttributeSet);
    }
}
