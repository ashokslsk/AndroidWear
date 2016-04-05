package com.ashokslsk.wearhelloworld;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity implements WearableListView.ClickListener{

    private WearableListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (WearableListView)findViewById(R.id.list);
        String[] items = getResources().getStringArray(R.array.list_items);
        WearableListAdapter adapter = new WearableListAdapter();
        adapter.setItems(new ArrayList(Arrays.asList(items)));
        mListView.setAdapter(adapter);

    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        Toast.makeText(this, ((TextView)viewHolder.itemView.findViewById(R.id.text))
                .getText(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTopEmptyRegionClick() {
        // No operation
    }

    private static final class WearableListAdapter extends WearableListView.Adapter{

        private List<String> items = new ArrayList<String>();

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_item, null));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            ((ViewHolder)holder).textView.setText(items.get(position));
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

        public void addItem(String item){
            this.items.add(item);
        }

        public static class ViewHolder extends WearableListView.ViewHolder{

            private TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.text);
            }
        }
    }
}
