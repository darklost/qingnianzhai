/**
 * 
 */
package com.qingnianzhai.android.api.base;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;

/**
 * @author liurenqiu520
 * 
 */
public abstract class BaseApi extends AsyncHttpClient {

	protected static final String ROOT_URL = "http://hbnd.qingnianzhai.com/api/";
	protected Context mContext;

	protected BaseApi(Context mContext) {
		this.mContext = mContext;

	}
}
