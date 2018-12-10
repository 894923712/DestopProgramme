package client;

import center.ChessMenu;

import javax.swing.*;

/**
 * 启动程序窗口
 * @author  罗明
 */
public class FiveChess {
    public static void main(String[] args) throws Exception {
        // 显示windows风格
        // String style = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        // 使用NimbusLookAndFeel外观
        String style = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
        UIManager.setLookAndFeel(style);
        new ChessMenu();
    }
}
