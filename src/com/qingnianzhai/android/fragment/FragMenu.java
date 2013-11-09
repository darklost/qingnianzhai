package com.qingnianzhai.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.qingnianzhai.android.R;
import com.qingnianzhai.android.activity.MainActivity;
import com.qingnianzhai.android.activity.base.MenuBaseActivity;

public class FragMenu extends SherlockFragment implements
		AdapterView.OnItemClickListener, View.OnClickListener {

	private ListView list;
	private ArrayAdapter<String> adapter;
	private Button settingButton;
	private ImageView frag_menu_icon;
	private TextView frag_menu_name;

	public static FragMenu getInstance() {
		FragMenu fragMenu = new FragMenu();
		Bundle args = new Bundle();
		fragMenu.setArguments(args);
		return fragMenu;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.menu));
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_menu, container, false);
		list = (ListView) view.findViewById(R.id.menu_list);
		frag_menu_name = (TextView) view.findViewById(R.id.frag_menu_name);
		view.findViewById(R.id.frag_menu_ucenter).setOnClickListener(this);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long arg3) {
//		for (int i = 0; i < parent.getCount(); i++) {
//			View v = parent.getChildAt(i);
//			if (position == i) {
//				v.setBackgroundResource(R.drawable.menu_selected_backgound);
//			} else {
//				v.setBackgroundColor(Color.TRANSPARENT);
//			}
//		}
		((MainActivity) getActivity()).menuOnClick(position);

	}

	public void listNotify() {
		try {
			adapter.notifyDataSetChanged();
		} catch (Exception ex) {
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.frag_menu_ucenter:
			((MenuBaseActivity) getActivity()).menuOnClick(-1);
			break;
		}
	}
}
