package com.qingnianzhai.android.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class Utils {
	public static final String TAG = "PushDemoActivity";
	public static final String RESPONSE_METHOD = "method";
	public static final String RESPONSE_CONTENT = "content";
	public static final String RESPONSE_ERRCODE = "errcode";
	protected static final String ACTION_LOGIN = "com.baidu.pushdemo.action.LOGIN";
	public static final String ACTION_MESSAGE = "com.baiud.pushdemo.action.MESSAGE";
	public static final String ACTION_RESPONSE = "bccsclient.action.RESPONSE";
	public static final String ACTION_SHOW_MESSAGE = "bccsclient.action.SHOW_MESSAGE";
	protected static final String EXTRA_ACCESS_TOKEN = "access_token";
	public static final String EXTRA_MESSAGE = "message";

	/** 时间格式化 */
	public static final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static String logStringCache = "";

	// 获取ApiKey
	public static String getMetaValue(Context context, String metaKey) {
		Bundle metaData = null;
		String apiKey = null;
		if (context == null || metaKey == null) {
			return null;
		}
		try {
			ApplicationInfo ai = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),
							PackageManager.GET_META_DATA);
			if (null != ai) {
				metaData = ai.metaData;
			}
			if (null != metaData) {
				apiKey = metaData.getString(metaKey);
			}
		} catch (NameNotFoundException e) {

		}
		return apiKey;
	}

	// 用share preference来实现是否绑定的开关。在ionBind且成功时设置true，unBind且成功时设置false
	public static boolean hasBind(Context context) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		String flag = sp.getString("bind_flag", "");
		if ("ok".equalsIgnoreCase(flag)) {
			return true;
		}
		return false;
	}

	public static void setBind(Context context, boolean flag) {
		String flagStr = "not";
		if (flag) {
			flagStr = "ok";
		}
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putString("bind_flag", flagStr);
		editor.commit();
	}

	public static List<String> getTagsList(String originalText) {
		if (originalText == null || originalText.equals("")) {
			return null;
		}
		List<String> tags = new ArrayList<String>();
		int indexOfComma = originalText.indexOf(',');
		String tag;
		while (indexOfComma != -1) {
			tag = originalText.substring(0, indexOfComma);
			tags.add(tag);

			originalText = originalText.substring(indexOfComma + 1);
			indexOfComma = originalText.indexOf(',');
		}

		tags.add(originalText);
		return tags;
	}

	public static String getLogText(Context context) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getString("log_text", "");
	}

	public static void setLogText(Context context, String text) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putString("log_text", text);
		editor.commit();
	}

	/**
	 * 计算某个时间与当前时间的差值。
	 * 
	 * @param dateString
	 *            时间字符格式，必须为：yyyy-MM-dd HH:mm:ss
	 * @return 格式化后的字串，如：1天前
	 */
	public static String convertTime(String dateString) {
		Date date = null;
		try {
			date = TIME_FORMATTER.parse(dateString);
		} catch (ParseException ex) {
			// 吃掉异常
		}
		if (date != null) {
			// 要计算的时间
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			// 当前时间
			Calendar calendarNow = new GregorianCalendar();
			calendarNow.setTime(new Date());

			int appYear = calendar.get(Calendar.YEAR);
			int currentYear = calendarNow.get(Calendar.YEAR);
			int appDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
			int currentDayOfYear = calendarNow.get(Calendar.DAY_OF_YEAR);

			if (appYear == currentYear) {
				int betweentDays = currentDayOfYear - appDayOfYear;
				if (betweentDays == 0) {
					return "今天";
				} else {
					return betweentDays + "天前";
				}
			} else {
				return (currentYear - appYear) + "年前";
			}
		}
		return dateString;
	}

	/**
	 * 计算某个时间与当前时间的差值。
	 * 
	 * @param date
	 *            时间戳
	 * @return 格式化后的字串，如：1天前
	 */
	public static String convertTime(long date) {
		// 要计算的时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(date * 1000);
		// 当前时间
		Calendar calendarNow = new GregorianCalendar();
		calendarNow.setTime(new Date());

		int appYear = calendar.get(Calendar.YEAR);
		System.out.println("appYear = " + appYear);
		int currentYear = calendarNow.get(Calendar.YEAR);
		System.out.println("currentYear = " + currentYear);
		int appDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		int currentDayOfYear = calendarNow.get(Calendar.DAY_OF_YEAR);

		if (appYear == currentYear) {
			int betweentDays = currentDayOfYear - appDayOfYear;
			if (betweentDays == 0) {
				return "今天";
			} else {
				return betweentDays + "天前";
			}
		} else {
			return (currentYear - appYear) + "年前";
		}
	}

}
