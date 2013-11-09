/**
 * 
 */
package com.qingnianzhai.android.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.qingnianzhai.android.R;

/**
 * @author 仁秋
 * 
 */
public class ForumAdapter2 extends CursorAdapter {

	public ForumAdapter2(Context context, Cursor c) {
		super(context, c, true);
	}

	@Override
	protected void onContentChanged() {
		super.onContentChanged();
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		ViewHolder holder = (ViewHolder) view.getTag();
		UrlImageViewHelper.setUrlDrawable(holder.icon,
				cursor.getString(cursor.getColumnIndex("avatar")),
				R.drawable.ic_launcher, true, false);
		holder.title.setText(cursor.getString(cursor.getColumnIndex("title")));
//		holder.info.setText(Home.creatInfo(cursor));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup group) {
		ViewHolder holder = new ViewHolder();
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.item_home, null);
		holder.icon = (ImageView) view
				.findViewById(R.id.item_home_icon_imageView);
		holder.title = (TextView) view
				.findViewById(R.id.item_home_title_textView);
		holder.info = (TextView) view
				.findViewById(R.id.item_home_info_textView);
		view.setTag(holder);
		return view;
	}

	class ViewHolder {
		ImageView icon;
		TextView title, info;
	}
}
