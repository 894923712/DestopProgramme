package task;

import javax.swing.*;

public class ClockTask extends MyThread{
    private long countdownStart = 0;
    private JTextField text = null;
    private long space = 1;

    /**
     * 设置相关参数
     * @param totalTime 倒计时长 {单位：毫秒; 示例：1 * 3 * 60 * 1000}
     * @param  _text 要显示的组件
     */
    public void setArgs(long totalTime, JTextField _text) {
        text = _text;
        countdownStart = System.currentTimeMillis() + totalTime; // 起始时间 + 计时时间
    }

    /**
     * 获取倒计时剩余时间
     * @return 单位(ms)
     */
    public long currTime(){
        return space;
    }

    /**
     * 格式化时间
     * @param number 时间 (ms)
     * @return
     */
    public String format(long number){
        String h = String.valueOf(number / 1000 / 60 / 60); // 时
        String m = String.valueOf(number / 1000 / 60 % 60); // 分
        String s = String.valueOf(number / 1000 % 60); // 秒
        return formatStr(h) + ":" + formatStr(m) + ":" + formatStr(s);
    }

    // 格式化参数(补零)
    private String formatStr(String args) {
        if (args.length() < 2) {
            args = "0" + args;
        }
        return args;
    }

    @Override
    public void runPersonelLogic() {
        space = countdownStart - System.currentTimeMillis(); // 时间差
        if(space > 0){
            text.setText(format(space));
        }
        try {
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ClockTask mt = new ClockTask();
        mt.start();
        Thread.sleep(1000);
        mt.setSuspend(true);
        System.out.println("stop");
        Thread.sleep(3000);
        mt.setSuspend(false);
    }
}
