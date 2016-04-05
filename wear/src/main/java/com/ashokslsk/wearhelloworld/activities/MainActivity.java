package com.ashokslsk.wearhelloworld.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokslsk.wearhelloworld.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity implements WearableListView.ClickListener {

    private WearableListView mListView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (WearableListView) findViewById(R.id.list);
        String[] items = getResources().getStringArray(R.array.list_items);
        WearableListAdapter adapter = new WearableListAdapter();
        adapter.setItems(new ArrayList(Arrays.asList(items)));
        mListView.setAdapter(adapter);
        mListView.setClickListener(this);
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        String title = ((TextView) viewHolder.itemView.findViewById(R.id.text))
                .getText().toString();
        Toast.makeText(this,title, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onClick:");
        if(getString(R.string.delayed_confirmation_view_activity).equalsIgnoreCase(title)){
            Intent intent = new Intent(this, DelayedConfirmationViewActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onTopEmptyRegionClick() {
        // No operation
    }

    private static final class WearableListAdapter extends WearableListView.Adapter {

        private List<String> items = new ArrayList<String>();

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_item, null));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            ((ViewHolder) holder).textView.setText(items.get(position));
            viewHolder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public List<String> getItems() {
            return items;
        }

        public void setItems(List<String> items) {
            this.items = items;
        }

        public void addItem(String item) {
            this.items.add(item);
        }

        public static class ViewHolder extends WearableListView.ViewHolder {

            private TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.text);
            }
        }
    }
}
