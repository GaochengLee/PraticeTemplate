package interview;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author 李高丞
 * @version 1.0
 */
public class MyThreadPool {
    private static final int WORK_NUM = 5;
    private static final int TASK_NUM = 100;
    private int workNum;
    private int taskNum;
    private final Set<WorkThread> workThreads;
    private final BlockingQueue<Runnable> taskQueue;

    public MyThreadPool() {
        this(WORK_NUM, TASK_NUM);
    }

    public MyThreadPool(int workNum, int taskNum) {
        if (workNum <= 0) workNum = WORK_NUM;
        if (taskNum <= 0) taskNum = TASK_NUM;

        taskQueue = new ArrayBlockingQueue<>(taskNum);
        workThreads = new HashSet<>();
        this.workNum = workNum;
        this.taskNum = taskNum;

        for (int i = 0; i < workNum; i++) {
            WorkThread workThread = new WorkThread("thread_" + i);
            workThread.start();
            workThreads.add(workThread);
        }
    }

    public void execute(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        if (workThreads == null || workThreads.isEmpty()) return;
        for (WorkThread workThread : workThreads) {
            workThread.stopWork();
            workThread = null;
        }
        workThreads.clear();
    }

    private class WorkThread extends Thread {
        public WorkThread(String name) {
            super();
            setName(name);
        }

        @Override
        public void run() {
            while (!interrupted()) {
                try {
                    Runnable runnable = taskQueue.take();
                    runnable.run();
                    runnable = null;
                } catch (InterruptedException e) {
                    interrupt();
                    e.printStackTrace();
                }
            }
        }

        public void stopWork() {
            interrupt();
        }
    }
}
