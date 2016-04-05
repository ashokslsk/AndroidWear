package com.ashokslsk.wearhelloworld.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;

import com.ashokslsk.wearhelloworld.models.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashok.kumar on 05/04/16.
 */
public class GridViewPagerAdapter extends FragmentGridPagerAdapter {

    private List<Row> mRows = new ArrayList<Row>();

    public GridViewPagerAdapter(FragmentManager fm) {
        super(fm);
        Row row = new Row(CardFragment.create("Row 1", "Page 1"));
        mRows.add(row);
        row = new Row(CardFragment.create("Row 2", "Page 1"), CardFragment.create("Row 2", "Page 2"), CardFragment.create("Row 2", "Page 3"));
        mRows.add(row);
        row = new Row(CardFragment.create("Row 3", "Page 1"), CardFragment.create("Row 2", "Page 2"));
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
}
