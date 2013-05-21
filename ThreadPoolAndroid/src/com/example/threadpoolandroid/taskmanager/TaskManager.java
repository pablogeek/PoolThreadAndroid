
package com.example.threadpoolandroid.taskmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by pablogeek on 17/05/13.
 */
public class TaskManager {

    static TaskManager sInstance;

    private final ThreadPoolExecutor mDecodeThreadPool;

    private final BlockingQueue<Runnable> mDecodeWorkQueue;

    private static int NUMBER_OF_CORES =
            Runtime.getRuntime().availableProcessors();

    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    static {

        sInstance = new TaskManager();
    }

    /**
     * Returns the TaskManager object
     * 
     * @return The global TaskManager object
     */
    public static TaskManager getInstance() {

        return sInstance;
    }

    private TaskManager() {

        mDecodeWorkQueue = new LinkedBlockingQueue<Runnable>();

        mDecodeThreadPool = new ThreadPoolExecutor(
                NUMBER_OF_CORES, // Initial pool size
                NUMBER_OF_CORES, // Max pool size
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                mDecodeWorkQueue);

    }

    Handler mHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {

            TransactionTask task = (TransactionTask) msg.obj;

            super.handleMessage(msg);
        }
    };

    public static void downloadTransactions(Handler updateUIHandler) {
        TransactionTask newsTask = new TransactionTask();
        newsTask.setUpdateUIHandler(updateUIHandler);
        sInstance.mDecodeThreadPool.execute(newsTask.getRunnable());

    }

    public void handler(TransactionTask task) {
        Message m = new Message();
        m.obj = task;
        task.getUpdateUIHandler().sendMessage(m);
    }

}
