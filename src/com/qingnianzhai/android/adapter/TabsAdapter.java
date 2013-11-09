package com.qingnianzhai.android.adapter;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class TabsAdapter extends FragmentPagerAdapter implements
		ActionBar.TabListener, ViewPager.OnPageChangeListener {
	private final SherlockFragmentActivity mContext;
	private final ActionBar mActionBar;
	private final ViewPager mViewPager;
	private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

	final class TabInfo {
		private Class<?> clss;
		private final Bundle args;
		private Fragment fragment;

		TabInfo(Fragment _fragment, Bundle _args) {
			args = _args;
			fragment = _fragment;
		}

		TabInfo(Class<?> _class, Bundle _args) {
			clss = _class;
			args = _args;
		}
	}

	public TabsAdapter(SherlockFragmentActivity activity, ViewPager pager) {
		super(activity.getSupportFragmentManager());
		mContext = activity;
		mActionBar = activity.getSupportActionBar();
		mViewPager = pager;
		mViewPager.setAdapter(this);
		mViewPager.setOnPageChangeListener(this);
	}

	public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args) {
		TabInfo info = new TabInfo(clss, args);
		tab.setTag(info);
		tab.setTabListener(this);
		mTabs.add(info);
		mActionBar.addTab(tab);
		notifyDataSetChanged();
	}

	public void addTab(ActionBar.Tab tab, Fragment fragment, Bundle args) {
		TabInfo info = new TabInfo(fragment, args);
		tab.setTag(info);
		tab.setTabListener(this);
		mTabs.add(info);
		mActionBar.addTab(tab);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mTabs.size();
	}

	@Override
	public Fragment getItem(int position) {
		TabInfo info = mTabs.get(position);
		if (info.clss == null) {
			info.fragment.setArguments(info.args);
			return info.fragment;
		} else {
			Fragment fuck = Fragment.instantiate(mContext, info.clss.getName(),
					info.args);
			info.fragment = fuck;
			return fuck;
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
	}

	@Override
	public void onPageSelected(int position) {
		mActionBar.setSelectedNavigationItem(position);
		if (mContext instanceof SlidingFragmentActivity) {
			SlidingFragmentActivity slContext = (SlidingFragmentActivity) mContext;
			switch (position) {
			case 0:
				slContext.getSlidingMenu().setTouchModeAbove(
						SlidingMenu.TOUCHMODE_FULLSCREEN);
				break;
			default:
				slContext.getSlidingMenu().setTouchModeAbove(
						SlidingMenu.TOUCHMODE_NONE);
				break;
			}
		}

	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Object tag = tab.getTag();
		for (int i = 0; i < mTabs.size(); i++) {
			if (mTabs.get(i) == tag) {
				mViewPager.setCurrentItem(i);
			}
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {

	}
}