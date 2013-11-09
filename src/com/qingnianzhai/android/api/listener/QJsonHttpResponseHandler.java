/**
 * 
 */
package com.qingnianzhai.android.api.listener;

import android.view.View;

import com.loopj.android.http.BaseJsonHttpResponseHandler;

/**
 * @author liurenqiu520
 * 
 */
public abstract class QJsonHttpResponseHandler<T> extends
		BaseJsonHttpResponseHandler<T> {

	protected View loadingView;

	protected QJsonHttpResponseHandler(View loadingView) {
		this.loadingView = loadingView;
	}

	@Override
	public void onStart() {
		super.onStart();
		if(loadingView !=null ){
			loadingView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onFinish() {
		super.onFinish();
		if(loadingView !=null ){
			loadingView.setVisibility(View.GONE);
		}
	}
}
