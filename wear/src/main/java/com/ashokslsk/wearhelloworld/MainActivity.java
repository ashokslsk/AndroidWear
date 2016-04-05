package com.ashokslsk.wearhelloworld;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;

public class MainActivity extends Activity {

    private WearableListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (WearableListView)findViewById(R.id.list);
    }
}
