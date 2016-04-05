package com.ashokslsk.wearhelloworld.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;

import com.ashokslsk.wearhelloworld.R;
import com.ashokslsk.wearhelloworld.fragments.CustomFragment;
import com.ashokslsk.wearhelloworld.models.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashok.kumar on 05/04/16.
 */
public class GridViewPagerAdapter extends FragmentGridPagerAdapter {

    private List<Row> mRows = new ArrayList<Row>();

    public GridViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        Row row = new Row(CardFragment.create("Row 1", "Page 1"));
        row.addBackground(context.getResources().getDrawable(R.drawable.bg1));
        mRows.add(row);

        row = new Row(CardFragment.create("Row 2", "Page 1"), CardFragment.create("Row 2", "Page 2"), CardFragment.create("Row 2", "Page 3"));
        row.addBackgrounds(context.getResources().getDrawable(R.drawable.bg2),context.getResources().getDrawable(R.drawable.bg3));
        mRows.add(row);


        row = new Row(CardFragment.create("Row 3", "Page 1"), CardFragment.create("Row 2", "Page 2"));
        row.addBackgrounds(context.getResources().getDrawable(R.drawable.bg4),context.getResources().getDrawable(R.drawable.bg5));
        mRows.add(row);

        row = new Row(new CustomFragment());
        mRows.add(row);

        row = new Row(CardFragment.create("Row 5", "Android wear is really fun to work with"));
        row.addBackground(context.getResources().getDrawable(R.drawable.bg1));
        mRows.add(row);
    }

    @Override
    public Fragment getFragment(int rowIndex, int columnIndex) {
        Row row = mRows.get(rowIndex);

        return row.getColumn(columnIndex);
    }

    @Override
    public int getRowCount() {
        return mRows.size();
    }

    @Override
    public int getColumnCount(int i) {
        return mRows.get(i).getColumnCount();
    }

    @Override
    public Drawable getBackgroundForRow(int row) {
        if(mRows.get(row).getBackgrounds()==null || mRows.get(row).getBackgrounds().isEmpty()){
            return super.getBackgroundForRow(row);
        }

        return mRows.get(row).getBackground(0);
    }

    @Override
    public Drawable getBackgroundForPage(int row, int column) {
        if(mRows.get(row).getBackgrounds()==null || column> mRows.get(row).getBackgrounds().size()-1){
            return super.getBackgroundForPage(row,column);
        }

        return mRows.get(row).getBackground(column);    }
}
