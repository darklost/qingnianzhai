/**
 * 
 */
package com.qingnianzhai.android.api;

import android.content.Context;

import com.qingnianzhai.android.api.base.BaseApi;
import com.qingnianzhai.android.api.listener.QJsonHttpResponseHandler;
import com.qingnianzhai.android.model.JsonHome;

/**
 * @author liurenqiu520
 * 
 */
public class ApiHome extends BaseApi {

	private static final String URL = "home_api/get_list/";

	public ApiHome(Context mContext) {
		super(mContext);
	}

	public void getList(int page,
			QJsonHttpResponseHandler<JsonHome> qJsonHttpResponseHandler) {
		super.get(ROOT_URL + URL + page, qJsonHttpResponseHandler);
	}

}
