package com.qingnianzhai.android;

import com.activeandroid.ActiveAndroid;
import com.baidu.frontia.FrontiaApplication;

/*
 * 如果您的工程中实现了Application的继承类，那么，您需要将父类改为com.baidu.frontia.FrontiaApplication。
 * 如果您没有实现Application的继承类，那么，请在AndroidManifest.xml的Application标签中增加属性： 
 * <application android:name="com.baidu.frontia.FrontiaApplication"
 * 。。。
 */
public class MyApplication extends FrontiaApplication {

	@Override
	public void onCreate() {
		super.onCreate();
		ActiveAndroid.initialize(this);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		ActiveAndroid.dispose();
	}

}
