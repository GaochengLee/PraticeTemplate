package interview;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockQueue<T> {
    private List<T> list = new ArrayList<>();
    private volatile int size;
    private volatile int capasity;
    private Lock lock = new ReentrantLock();
    private final Condition isNull = lock.newCondition();
    private final Condition isFull = lock.newCondition();

    public MyBlockQueue(int capasity) {
        this.capasity = capasity;
    }

    public void add(T data) {
        try {
            lock.lock();
            try {
                while (size >= capasity) {
                    System.out.println("阻塞队列已满");
                    isFull.await();
                }
            } catch (InterruptedException e) {
                isFull.signal();
                e.printStackTrace();
            }

            size++;
            list.add(data);
            isNull.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        try {
            lock.lock();
            try {
                while (size == 0) {
                    System.out.println("阻塞队列为空");
                    isNull.await();
                }
            } catch (InterruptedException e) {
                isNull.signal();
                e.printStackTrace();
            }

            size--;
            T data = list.get(0);
            list.remove(0);
            isFull.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }
}