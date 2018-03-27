package com.ex.admin.lookatmebeta;

import android.app.Application;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * Created by Admin on 27.03.2018.
 */

public class Worker  implements IWorker {
    @Override
    public void actionShowMessage(TextView textView, String txt) {

        textView.setText(textView.getText().toString() + "\n\n" + txt);
    }

}
