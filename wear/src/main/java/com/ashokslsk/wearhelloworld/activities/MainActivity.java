package com.ashokslsk.wearhelloworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.WearableListView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashokslsk.wearhelloworld.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends WearableActivity implements WearableListView.ClickListener {

    public static boolean isAmbientMode = false;
    private BoxInsetLayout mBoxInsetLayout;
    private WearableListView mListView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAmbientEnabled();

        mListView = (WearableListView) findViewById(R.id.list);
        mBoxInsetLayout = (BoxInsetLayout) findViewById(R.id.parent);
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
//        Toast.makeText(this,title, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onClick:");
        if(getString(R.string.delayed_confirmation_view_activity).equalsIgnoreCase(title)){
            Intent intent = new Intent(this, DelayedConfirmationViewActivity.class);
            startActivity(intent);
        }else if(getString(R.string.grid_view_pager_activity).equalsIgnoreCase(title)){
            Intent intent = new Intent(this, GridViewPagerActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onTopEmptyRegionClick() {
        // No operation
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        isAmbientMode = true;
        mListView.setBackgroundColor(getResources().getColor(android.R.color.black));
        mBoxInsetLayout.setBackgroundColor(getResources().getColor(android.R.color.black));
        mListView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onExitAmbient() {
        super.onExitAmbient();
        isAmbientMode = false;
        mListView.setBackgroundColor(getResources().getColor(android.R.color.white));
        mBoxInsetLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
        mListView.getAdapter().notifyDataSetChanged();
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

            if(MainActivity.isAmbientMode){
                viewHolder.textView.setTextColor(viewHolder.textView.getResources().getColor(android.R.color.white));
            }else{
                viewHolder.textView.setTextColor(viewHolder.textView.getResources().getColor(android.R.color.black));
            }

            viewHolder.itemView.setTag(position);
        }

        @Override
        public void onViewRecycled(WearableListView.ViewHolder holder) {
            super.onViewRecycled(holder);

            ViewHolder viewholder = (ViewHolder) holder;
            if(MainActivity.isAmbientMode){
                viewholder.textView.setTextColor(viewholder.textView.getResources().getColor(android.R.color.white));
            }
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
