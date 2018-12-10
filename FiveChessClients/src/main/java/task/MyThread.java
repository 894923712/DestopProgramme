package task;

public abstract class MyThread extends Thread {
    // 该方法执行自己的逻辑
    public abstract void runPersonelLogic();

    // 其实逻辑
    private Long control = new Long(0L);
    // 初始化
    private boolean suspend = true;

    public boolean isSuspend() {
        return suspend;
    }

    /**
     * 暂停线程
     * @param suspend
     */
    public void setSuspend(boolean suspend) {
        if (!suspend) {
            synchronized (control) {
                control.notify();
            }
        }
        this.suspend = suspend;
    }

    // 重写Thread的run方法
    @Override
    public void run() {
        while (true) {
            synchronized (control) {
                if (suspend) {
                    try {
                        control.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            this.runPersonelLogic();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread mt = new MyThread() {

            @Override
            public void runPersonelLogic() {
                System.out.println("run");
            }
        };
        mt.start();
        Thread.sleep(1000);
        mt.setSuspend(true);
        System.out.println("stop");
        Thread.sleep(3000);
        mt.setSuspend(false);
    }
}
