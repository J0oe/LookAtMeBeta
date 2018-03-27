package com.ex.admin.lookatmebeta;

import android.widget.TextView;

/**
 * Created by Admin on 27.03.2018.
 */

public class Collector implements ICollector {
    Worker worker = new Worker();

    @Override
    public void showMeMessage(TextView textView, String txt) {
        worker.actionShowMessage(textView, txt);
    }
}
