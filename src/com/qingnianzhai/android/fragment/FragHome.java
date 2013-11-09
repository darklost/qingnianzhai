/**
 * 
 */
package com.qingnianzhai.android.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qingnianzhai.android.R;
import com.qingnianzhai.android.adapter.ForumAdapter;
import com.qingnianzhai.android.api.ApiHome;
import com.qingnianzhai.android.api.listener.QJsonHttpResponseHandler;
import com.qingnianzhai.android.model.JsonHome;
import com.qingnianzhai.android.model.JsonHome.Home;

/**
 * @author liurenqiu520
 * 
 */
public class FragHome extends SherlockFragment {

	private ForumAdapter forumAdapter;
	private PullToRefreshListView listView;
	private View loadingView;

	private List<Home> homeList;

	private ApiHome apiHome;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		apiHome = new ApiHome(getActivity());
		forumAdapter = new ForumAdapter(getActivity(), homeList);
		listView.setAdapter(forumAdapter);

		doGetData(1);
	}

	private void doGetData(int page) {
		apiHome.getList(page, new QJsonHttpResponseHandler<JsonHome>(
				loadingView) {

			@Override
			protected JsonHome parseResponse(String responseBody)
					throws Throwable {
				JsonHome home = null;
				try {
					home = JSON.parseObject(responseBody, JsonHome.class);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return home;
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawResponse, JsonHome response) {
				System.out.println(rawResponse);
				if (response != null && response.getList() != null) {
					listView.setVisibility(View.VISIBLE);
					homeList.addAll(response.getList());
					System.out.println(homeList.size());
					forumAdapter.notifyDataSetChanged();
				}
			}
			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable e, String rawData, JsonHome errorResponse) {
				// notice failure
			}
		});
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		homeList = new ArrayList<JsonHome.Home>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_home, container, false);
		listView = (PullToRefreshListView) view
				.findViewById(R.id.pull_refresh_list);
		loadingView = view.findViewById(R.id.list_loading);
		return view;
	}
}
