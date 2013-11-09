//package com.qingnianzhai.android.fragment;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.jraf.android.backport.switchwidget.Switch;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CompoundButton;
//import android.widget.CompoundButton.OnCheckedChangeListener;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.actionbarsherlock.app.SherlockFragment;
//import com.baidu.android.pushservice.PushManager;
//import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
//import com.qingniantuzhai.liball.R;
//import com.qingniantuzhai.liball.activity.ActLogin;
//import com.qingniantuzhai.liball.activity.ActSetting;
//import com.qingniantuzhai.liball.activity.MainActivity;
//import com.qingniantuzhai.liball.config.KEY;
//import com.qingniantuzhai.liball.model.QUser;
//import com.qingniantuzhai.liball.share.Info;
//import com.qingniantuzhai.liball.utils.ActivityUtils;
//
//public class FragRightMenu extends SherlockFragment implements
//		View.OnClickListener, OnCheckedChangeListener {
//
//	public static final int CODE_LOGIN = 100;
//
//	private ImageView frag_menu_icon;
//	private TextView frag_menu_name, frag_menu_label;
//
//	private View frag_menu_ucenter;
//
//	private Switch pushSwitch, nightSwitch;
//
//	private QUser qUser;
//
//	public static FragRightMenu getInstance() {
//		FragRightMenu fragMenu = new FragRightMenu();
//		Bundle args = new Bundle();
//		fragMenu.setArguments(args);
//		return fragMenu;
//	}
//
//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
//		super.onActivityCreated(savedInstanceState);
//		pushSwitch.setChecked(Info.getBoolean(getActivity(),
//				Info.KEY_POST_PUSH_ON));
//		nightSwitch.setChecked(Info
//				.getBoolean(getActivity(), Info.KEY_NIGHT_ON));
//
//		qUser = QUser.getUser();
//		initLogin();
//	}
//
//	private void initLogin() {
//		if (qUser == null) {
//			frag_menu_name.setText("未登录");
//			frag_menu_label.setText("点击登陆");
//			frag_menu_ucenter.setTag(null);
//			frag_menu_icon.setImageResource(R.drawable.ic_launcher_gray);
//			return;
//		}
//		UrlImageViewHelper.setUrlDrawable(frag_menu_icon, qUser.getAvatar(),
//				R.drawable.ic_launcher_gray, true);
//		frag_menu_name.setText(qUser.getDisplay_name());
//		frag_menu_label.setText("点击注销");
//		frag_menu_ucenter.setTag(qUser.getUser_id());
//	}
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		View view = inflater
//				.inflate(R.layout.frag_right_menu, container, false);
//		view.findViewById(R.id.menu_feed_textView).setOnClickListener(this);
//		view.findViewById(R.id.menu_collection_textView).setOnClickListener(
//				this);
//		view.findViewById(R.id.menu_setting_textView).setOnClickListener(this);
//
//		pushSwitch = (Switch) view.findViewById(R.id.push_switch);
//		pushSwitch.setOnCheckedChangeListener(this);
//		nightSwitch = (Switch) view.findViewById(R.id.night_switch);
//		nightSwitch.setOnCheckedChangeListener(this);
//		frag_menu_ucenter = view.findViewById(R.id.frag_menu_ucenter);
//		frag_menu_ucenter.setOnClickListener(this);
//		frag_menu_icon = (ImageView) view.findViewById(R.id.frag_menu_icon);
//		frag_menu_name = (TextView) view.findViewById(R.id.frag_menu_name);
//		frag_menu_label = (TextView) view.findViewById(R.id.frag_menu_label);
//		return view;
//	}
//
//	@Override
//	public void onResume() {
//		super.onResume();
//	}
//
//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//	}
//
//	@Override
//	public void onClick(View v) {
//		((MainActivity) getActivity()).showContent();
//		switch (v.getId()) {
//		case R.id.frag_menu_ucenter:
//			// 跳转到登录
//			if (frag_menu_ucenter.getTag() != null) {
//				// 注销登录
//				showLogoutDialog();
//			} else {
//				// 登陆
//				Intent intent = new Intent(getActivity(), ActLogin.class);
//				ActivityUtils.startActivityForResult(getActivity(), this,
//						intent, CODE_LOGIN);
//			}
//			break;
//		case R.id.menu_feed_textView:
//
//			break;
//		case R.id.menu_collection_textView:
//
//			break;
//		case R.id.menu_setting_textView:
//			Intent intent = new Intent(getActivity(), ActSetting.class);
//			ActivityUtils.startActivity(getActivity(), intent);
//			break;
//		}
//	}
//
//	@Override
//	public void onCheckedChanged(CompoundButton view, boolean checked) {
//		switch (view.getId()) {
//		case R.id.push_switch:
//			if (checked) {
//				List<String> tags = new ArrayList<String>();
//				tags.add(KEY.PUSH_TAG);
//				PushManager.setTags(getActivity(), tags);
//			} else {
//				List<String> tags = new ArrayList<String>();
//				tags.add(KEY.PUSH_TAG);
//				PushManager.delTags(getActivity(), tags);
//			}
//			Info.putObject(getActivity(), Info.KEY_POST_PUSH_ON, checked);
//			break;
//		case R.id.night_switch:
//			if (checked) {
//				((MainActivity) getActivity()).night();
//			} else {
//				((MainActivity) getActivity()).day();
//			}
//			Info.putObject(getActivity(), Info.KEY_NIGHT_ON, checked);
//			break;
//		}
//	}
//
//	public void showLogoutDialog() {
//		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//		builder.setTitle("温馨提示");
//		builder.setMessage("你确定要注销登录吗？");
//		builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				dialog.dismiss();
//			}
//		});
//		builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				QUser.deletUser(frag_menu_ucenter.getTag().toString());
//				qUser = null;
//				initLogin();
//			}
//		});
//		builder.create().show();
//	}
//
//	@Override
//	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//		if (requestCode == CODE_LOGIN) {
//			if (resultCode == Activity.RESULT_OK) {
//				// 登陆成功
//				qUser = QUser.getUser();
//				initLogin();
//			}
//		}
//	}
//}
