
package com.example.threadpoolandroid;

import com.example.threadpoolandroid.taskmanager.TransactionTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    Handler handlerUpdateUI = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {

            TransactionTask t = (TransactionTask) msg.obj;

            t.getTasks();

            // drawDataTransactions();

            super.handleMessage(msg);
        }
    };

}
