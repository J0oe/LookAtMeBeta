package com.ex.admin.lookatmebeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class MainActivity extends AppCompatActivity {
    Collector collector;
    TextView textView;
    OkHttpClient client;
    static WebSocket webSocket;

    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_STATUS = 1000;


        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            MainActivity.webSocket = webSocket;
            webSocket.send("test1");
            webSocket.send("tes333");
            webSocket.send("test22");
           // webSocket.close(NORMAL_STATUS, "финал");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            actionRun("mess : " + text);
        }


        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_STATUS, null);
            actionRun("mess : " + reason);

        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {

            actionRun("mess : " + t.getMessage());

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        collector = new Collector();
        textView = findViewById(R.id.idTextEX);
        client = new OkHttpClient();

        start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webSocket.send("12");
    }

    private void actionRun(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                collector.showMeMessage(textView, s);
            }
        });
    }

    private void start() {
        Request request = new Request.Builder().url("ws://echo.websocket.org").build();

        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }
}
