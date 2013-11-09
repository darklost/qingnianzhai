/**
 * 
 */
package com.qingnianzhai.android.activity.base;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.baidu.mobstat.StatService;
import com.qingnianzhai.android.share.Info;
import com.qingnianzhai.android.utils.ActivityUtils;
import com.qingnianzhai.android.utils.SampleGuest;

/**
 * @author 仁秋
 * 
 */
public abstract class BaseActivity extends SherlockFragmentActivity {

	public GestureDetector gestureDetector;

	private WindowManager mWindowManager;
	private TextView masterTextView;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		SampleGuest gestureListener = new SampleGuest(this);
		gestureDetector = new GestureDetector(this, gestureListener);
		mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		findView();
		setListener();
		initData();
		initView();
	}

	private void initView() {
		if (Info.getBoolean(this, Info.KEY_NIGHT_ON)) {
			night();
		}else{
			day();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
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

	protected abstract void initData();

	protected abstract void setListener();

	protected abstract void findView();

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

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		gestureDetector.onTouchEvent(event);
		return super.dispatchTouchEvent(event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ActivityUtils.finish(this);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
