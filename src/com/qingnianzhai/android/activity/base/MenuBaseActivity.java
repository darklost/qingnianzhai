package com.qingnianzhai.android.activity.base;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.view.MenuItem;
import com.baidu.mobstat.StatService;
import com.qingnianzhai.android.R;
import com.qingnianzhai.android.fragment.FragMenu;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public abstract class MenuBaseActivity extends SlidingFragmentActivity {

	private FragMenu fragMenu;
	private long lastPressTime;
	private static final long DOUBLE_PRESS_INTERVAL = 2000;
	
	private WindowManager mWindowManager;
	private TextView masterTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			savedInstanceState.remove("android:support:fragments");
		}
		super.onCreate(savedInstanceState);
		initMenu();
		getSupportActionBar().setHomeButtonEnabled(true);
		mWindowManager = (WindowManager) getSystemService(
				Context.WINDOW_SERVICE);
	}

	public void initMenu() {
		// check if the content frame contains the menu frame
		setBehindContentView(R.layout.menu_frame);
		getSlidingMenu().setSlidingEnabled(true);
		getSlidingMenu()
				.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
		getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
		getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
		
		fragMenu = FragMenu.getInstance();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_container, fragMenu).commit();
//		getSupportFragmentManager().beginTransaction()
//				.replace(R.id.menu_frame_two, new FragRightMenu()).commit();
		
		final SlidingMenu sm = getSlidingMenu();
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindScrollScale(0.25f);
		sm.setFadeDegree(0.25f);
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		initView();
		findView();
		setListener();
		initData();
	}

	private void initView() {

	}

	public void menuOnClick(int position) {
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		super.onPause();
		StatService.onPause(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		StatService.onResume(this);
	}

	public void day() {
		if (masterTextView != null) {
			mWindowManager.removeView(masterTextView);
		}
	}

	public void night() {
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
						| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);

		lp.gravity = Gravity.BOTTOM;// 可以自定义显示的位置
		lp.y = 10;// 距离底部的距离是10像素 如果是 top 就是距离top是10像素
		masterTextView = new TextView(this);

		masterTextView.setBackgroundColor(0x99000000);
		mWindowManager.addView(masterTextView, lp);
	}
	
	public void exit() {
		long pressTime = System.currentTimeMillis();
		if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
			finish();
		} else {
			Toast.makeText(this, getString(R.string.toast_press_back),
					Toast.LENGTH_SHORT).show();
		}
		lastPressTime = pressTime;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (!getSlidingMenu().isMenuShowing()
				&& keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	protected abstract void initData();

	protected abstract void setListener();

	protected abstract void findView();

}
