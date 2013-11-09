package com.qingnianzhai.android.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.qingnianzhai.android.R;
import com.qingnianzhai.android.activity.base.MenuBaseActivity;
import com.qingnianzhai.android.adapter.TabsAdapter;
import com.qingnianzhai.android.fragment.FragHome;

public class MainActivity extends MenuBaseActivity {

	private ViewPager viewPager;
	private TabsAdapter tabsAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void initData() {
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.home_container,
						Fragment.instantiate(this, FragHome.class.getName()))
				.commit();
	}

	@Override
	protected void setListener() {

	}

	@Override
	protected void findView() {
		viewPager = (ViewPager) findViewById(R.id.home_container);
	}

}
