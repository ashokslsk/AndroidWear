package com.ashokslsk.wearhelloworld.models;

import android.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashok.kumar on 05/04/16.
 */
public class Row {

    private final List<Fragment> columns = new ArrayList<Fragment>();

    public Row(Fragment... fragments) {

        for (Fragment fragment:fragments){
            columns.add(fragment);
        }
    }

    public Fragment getColumn(int i){
        return columns.get(i);
    }

    public int getColumnCount(){
        return columns.size();
    }

}
