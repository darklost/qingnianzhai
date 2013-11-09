package com.qingnianzhai.android.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.qingnianzhai.android.R;

public class ActivityUtils {

	public static void finish(Activity context) {
		context.finish();
		finishAnim(context);
	}
	
	public static void finishAnim(Activity context) {
		context.overridePendingTransition(R.anim.qingnianzhai_fade_in,
				R.anim.qingnianzhai_slide_out_from_right);
	}

	public static void startActivity(Activity context, Intent intent) {
		context.startActivity(intent);
		context.overridePendingTransition(R.anim.qingnianzhai_slide_in_from_right,
				R.anim.qingnianzhai_fade_out);
	}

	public static void startActivityForResult(Activity context, Intent intent,
			int requestCode) {
		context.startActivityForResult(intent, requestCode);
		context.overridePendingTransition(R.anim.qingnianzhai_slide_in_from_right,
				R.anim.qingnianzhai_fade_out);
	}
	
	public static void startActivityForResult(Activity context, Fragment fragment, Intent intent,
			int requestCode) {
		fragment.startActivityForResult(intent, requestCode);
		context.overridePendingTransition(R.anim.qingnianzhai_slide_in_from_right,
				R.anim.qingnianzhai_fade_out);
	}
}
