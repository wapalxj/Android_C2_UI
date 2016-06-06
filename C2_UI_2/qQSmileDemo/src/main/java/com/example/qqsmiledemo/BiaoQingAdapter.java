package com.example.qqsmiledemo;

import java.util.ArrayList;
import java.util.List;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class BiaoQingAdapter extends FragmentPagerAdapter {

private List<Fragment> Filmfragments = new ArrayList<>();
	
	public BiaoQingAdapter(FragmentManager fm) {
		super(fm);
		Filmfragments.add(new MyBiaoQingFragment1());
		Filmfragments.add(new MyBiaoQingFragment2());
	}

	@Override
	public Fragment getItem(int pos) {
		return Filmfragments.get(pos);
	}

	@Override
	public int getCount() {
		return Filmfragments.size();
	}
	

}
