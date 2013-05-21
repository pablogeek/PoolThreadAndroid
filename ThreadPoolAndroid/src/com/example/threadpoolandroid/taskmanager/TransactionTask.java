
package com.example.threadpoolandroid.taskmanager;

import com.example.threadpoolandroid.model.Transaction;
import com.example.threadpoolandroid.runables.DownloadTransactionRunnable;

import android.os.Handler;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pablogeek on 17/05/13.
 */
public class TransactionTask {

    private final DownloadTransactionRunnable runnable;

    Handler updateUIHandler;

    private HashMap<String, List<Transaction>> tasks;

    public TransactionTask() {
        runnable = new DownloadTransactionRunnable(this);
    }

    public void handleState() {
        TaskManager.getInstance().handler(this);
    }

    public DownloadTransactionRunnable getRunnable() {
        return runnable;
    }

    public HashMap<String, List<Transaction>> getTasks() {
        return tasks;
    }

    public void setTasks(HashMap<String, List<Transaction>> tasks) {
        this.tasks = tasks;
    }

    public Handler getUpdateUIHandler() {
        return updateUIHandler;
    }

    public void setUpdateUIHandler(Handler updateUIHandler) {
        this.updateUIHandler = updateUIHandler;
    }

}
