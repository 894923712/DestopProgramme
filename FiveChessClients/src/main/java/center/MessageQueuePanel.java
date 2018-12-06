package center;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author LuoMing luom@3vjia.com
 * 消息队列
 * @since 2018-12-06 16:36
 */
public class MessageQueuePanel extends JPanel implements KeyListener {
    public JTextField pk,countdownText,totalTimeText;
    public MessageQueuePanel(){
        this.setLayout(null);
        pk=new JTextField("PVP状态");
        countdownText=new JTextField();
    }
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}
