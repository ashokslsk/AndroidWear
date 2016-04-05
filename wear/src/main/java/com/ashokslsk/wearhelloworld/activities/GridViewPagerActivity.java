package com.ashokslsk.wearhelloworld.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.GridViewPager;

import com.ashokslsk.wearhelloworld.R;
import com.ashokslsk.wearhelloworld.adapters.GridViewPagerAdapter;

public class GridViewPagerActivity extends Activity {

    private GridViewPager mGridViewPager;
    private DotsPageIndicator mPageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_pager);
        mGridViewPager = (GridViewPager) findViewById(R.id.pager);
        mPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);
        mPageIndicator.setPager(mGridViewPager);
        GridViewPagerAdapter adapter = new GridViewPagerAdapter(this, getFragmentManager());
        mGridViewPager.setAdapter(adapter);

    }
}
