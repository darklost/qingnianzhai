/**
 * 
 */
package com.qingnianzhai.android.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.qingnianzhai.android.R;
import com.qingnianzhai.android.model.JsonHome.Home;

/**
 * @author 仁秋
 * 
 */
public class ForumAdapter extends BaseAdapter {

	private List<Home> list;
	private Context context;

	public ForumAdapter(Context context, List<Home> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Home getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		Home home = list.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.item_home, null);
			holder.icon = (ImageView) convertView
					.findViewById(R.id.item_home_icon_imageView);
			holder.title = (TextView) convertView
					.findViewById(R.id.item_home_title_textView);
			holder.info = (TextView) convertView
					.findViewById(R.id.item_home_info_textView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		UrlImageViewHelper.setUrlDrawable(holder.icon, home.getAvatar(),
				R.drawable.ic_launcher, true, false);
		holder.title.setText(home.getTitle());
		holder.info.setText(home.creatInfo());
		return convertView;
	}

	class ViewHolder {
		ImageView icon;
		TextView title, info;
	}
}
