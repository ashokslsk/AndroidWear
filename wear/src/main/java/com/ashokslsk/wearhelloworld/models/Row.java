package com.ashokslsk.wearhelloworld.models;

import android.app.Fragment;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashok.kumar on 05/04/16.
 */
public class Row {

    private final List<Fragment> columns = new ArrayList<Fragment>();
    private List<Drawable> fragmentBackgrounds = new ArrayList<Drawable>();

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

    public List<Drawable> getBackgrounds(){
        return fragmentBackgrounds;
    }

    public void addBackground(Drawable background){
        this.fragmentBackgrounds.add(background);

    }

    public void addBackgrounds(Drawable...backgrounds){
        for (Drawable Background: backgrounds) {
            fragmentBackgrounds.add(Background);
        }
    }

    public Drawable getBackground(int index){
        if(index <= fragmentBackgrounds.size()-1)
        return fragmentBackgrounds.get(index);
        else{
            return null;
        }
    }

}
