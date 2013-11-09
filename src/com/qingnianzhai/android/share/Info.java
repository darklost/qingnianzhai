package com.qingnianzhai.android.share;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Info {


	private static final String SHARE_NAME = "qingnianzhai";
	public static final String KEY_OPENED_MENU = "com.qingniantuzhai.liball.share.KEY_OPENED_MENU";
	
	
	public static final String KEY_NIGHT_ON = "com.qingniantuzhai.liball.share.KEY_NIGHT_ON";

	public static void putObject(Context context, String key, Object value) {
		SharedPreferences info = context.getSharedPreferences(SHARE_NAME,
				Context.MODE_PRIVATE);
		Editor edit = info.edit();
		if (value instanceof String) {
			edit.putString(key, value.toString());
		} else if (value instanceof Boolean) {
			edit.putBoolean(key, (Boolean) value);
		} else if (value instanceof Integer) {
			edit.putInt(key, (Integer) value);
		} else if (value instanceof Long) {
			edit.putLong(key, (Long) value);
		} else if (value instanceof Float) {
			edit.putFloat(key, (Float) value);
		}
		edit.commit();
	}

	public static boolean contains(Context context, String key){
		SharedPreferences info = context.getSharedPreferences(SHARE_NAME,
				Context.MODE_PRIVATE);
		return info.contains(key);
	}
	public static void clearKey(Context context, String key) {
		SharedPreferences info = context.getSharedPreferences(SHARE_NAME,
				Context.MODE_PRIVATE);
		info.edit().remove(key).commit();
	}

	public static String getString(Context context, String key) {
		SharedPreferences info = context.getSharedPreferences(SHARE_NAME,
				Context.MODE_PRIVATE);
		return info.getString(key, "unknown");
	}

	public static boolean getBoolean(Context context, String key) {
		SharedPreferences info = context.getSharedPreferences(SHARE_NAME,
				Context.MODE_PRIVATE);
		return info.getBoolean(key, false);
	}

	public static int getInt(Context context, String key) {
		SharedPreferences info = context.getSharedPreferences(SHARE_NAME,
				Context.MODE_PRIVATE);
		return info.getInt(key, 0);
	}

	public static long getLong(Context context, String key) {
		SharedPreferences info = context.getSharedPreferences(SHARE_NAME,
				Context.MODE_PRIVATE);
		return info.getLong(key, 0);
	}
}
