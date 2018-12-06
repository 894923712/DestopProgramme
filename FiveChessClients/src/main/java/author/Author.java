package author;

import javax.swing.*;
import java.awt.*;

/**
 * @author LuoMing luom@3vjia.com
 * 关于作者
 * @since 2018-12-06 16:01
 */
public class Author extends JFrame {
      JPanel authorPanel;
      public Author(){
          this.setTitle("关于作者");
          authorPanel=new JPanel(new GridLayout(7,1,1,5));
          authorPanel.add(new JLabel("罗明"));
          authorPanel.add(new JLabel("QQ:894923712"));
          authorPanel.add(new JLabel("weiChart:894923712"));
          authorPanel.add(new JLabel("Tel:13265676924"));
          this.add(authorPanel);
          this.setSize(200,200);
          this.setLocationRelativeTo(null);
          this.setVisible(true);
      }
}
