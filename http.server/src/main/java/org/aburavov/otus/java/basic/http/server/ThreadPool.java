package org.aburavov.otus.java.basic.http.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private final BlockingQueue<Runnable> queue;

    private final static Logger logger = LogManager.getLogger(ThreadPool.class.getName());

    public ThreadPool(int size) {
        queue = new LinkedBlockingQueue<>();

        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        queue.take().run();
                    } catch (InterruptedException e) {
                        logger.error("Run: thread interrupted: {}", e.toString());
                    }
                }
            }).start();
        }
    }

    public void execute(Runnable task) {
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            logger.error("Execute: thread interrupted: {}", e.toString());
        }
    }
}
