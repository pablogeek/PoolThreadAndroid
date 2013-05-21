
package com.example.threadpoolandroid.runables;

import com.example.threadpoolandroid.taskmanager.TransactionTask;

public class DownloadTransactionRunnable implements Runnable {

    private final TransactionTask task;

    public DownloadTransactionRunnable(TransactionTask task) {
        this.task = task;
    }

    @Override
    public void run() {
        task.setTasks(WebServices.getTransactions());
        task.handleState();
    }
}
