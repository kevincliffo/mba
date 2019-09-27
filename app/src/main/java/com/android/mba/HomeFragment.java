package com.android.mba;

import android.support.v4.app.Fragment; import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by tutlane on 09-01-2018.
 */

public class HomeFragment extends Fragment {

    ViewPager viewPager;
    ImagesSwipeAdapter adapter;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){


        view = inflater.inflate(R.layout.homelayout, viewGroup, false);

        viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        adapter = new ImagesSwipeAdapter(view.getContext());

        viewPager.setAdapter(adapter);
        //return inflater.inflate(R.layout.homelayout, viewGroup, false);
        return view;
    }
}
