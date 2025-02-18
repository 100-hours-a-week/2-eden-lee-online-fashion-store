package threads;

public class TimeOutThread extends Thread{
    private int timeout;    // 초 단위
    private Thread targetThread;

    public TimeOutThread(int timeout, Thread targetThread) {
        this.timeout = timeout;
        this.targetThread = targetThread;
    }

    public void run() {
        try {
            Thread.sleep(timeout * 1000);
            System.out.println("\n"+ timeout + "초 간 응답이 없어 프로그램이 종료됩니다.");
            targetThread.interrupt();
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
    }

    public void stopTimer() {
        this.interrupt();
    }
}
