/**
 * 
 */
package com.qingnianzhai.android.model;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.qingnianzhai.android.model.base.Base;
import com.qingnianzhai.android.utils.Utils;

/**
 * @author liurenqiu520
 *
 */
public class JsonHome extends Base {

	public List<Home> list;
	
	public List<Home> getList() {
		return list;
	}
	public void setList(List<Home> list) {
		this.list = list;
	}
	@Table(name = "ForumList")
	public static class Home extends Model {

		@Column(name = "fid")
		public String fid;

		@Column(name = "cid")
		public String cid;

		@Column(name = "uid")
		public String uid;

		@Column(name = "ruid")
		public String ruid;

		@Column(name = "title")
		public String title;

		@Column(name = "keywords")
		public String keywords;

		@Column(name = "addtime")
		public String addtime;

		@Column(name = "updatetime")
		public String updatetime;

		@Column(name = "lastreply")
		public String lastreply;

		@Column(name = "views")
		public String views;

		@Column(name = "comments")
		public String comments;

		@Column(name = "favorites")
		public String favorites;

		@Column(name = "closecomment")
		public String closecomment;

		@Column(name = "is_top")
		public String is_top;

		@Column(name = "is_hidden")
		public String is_hidden;

		@Column(name = "ord")
		public String ord;

		@Column(name = "username")
		public String username;

		@Column(name = "avatar")
		public String avatar;

		@Column(name = "rname")
		public String rname;

		@Column(name = "cname")
		public String cname;

		public String getFid() {
			return fid;
		}

		public void setFid(String fid) {
			this.fid = fid;
		}

		public String getCid() {
			return cid;
		}

		public void setCid(String cid) {
			this.cid = cid;
		}

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getRuid() {
			return ruid;
		}

		public void setRuid(String ruid) {
			this.ruid = ruid;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getKeywords() {
			return keywords;
		}

		public void setKeywords(String keywords) {
			this.keywords = keywords;
		}

		public String getAddtime() {
			return addtime;
		}

		public void setAddtime(String addtime) {
			this.addtime = addtime;
		}

		public String getUpdatetime() {
			return updatetime;
		}

		public void setUpdatetime(String updatetime) {
			this.updatetime = updatetime;
		}

		public String getLastreply() {
			return lastreply;
		}

		public void setLastreply(String lastreply) {
			this.lastreply = lastreply;
		}

		public String getViews() {
			return views;
		}

		public void setViews(String views) {
			this.views = views;
		}

		public String getComments() {
			return comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}

		public String getFavorites() {
			return favorites;
		}

		public void setFavorites(String favorites) {
			this.favorites = favorites;
		}

		public String getClosecomment() {
			return closecomment;
		}

		public void setClosecomment(String closecomment) {
			this.closecomment = closecomment;
		}

		public String getIs_top() {
			return is_top;
		}

		public void setIs_top(String is_top) {
			this.is_top = is_top;
		}

		public String getIs_hidden() {
			return is_hidden;
		}

		public void setIs_hidden(String is_hidden) {
			this.is_hidden = is_hidden;
		}

		public String getOrd() {
			return ord;
		}

		public void setOrd(String ord) {
			this.ord = ord;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public String getRname() {
			return rname;
		}

		public void setRname(String rname) {
			this.rname = rname;
		}

		public String getCname() {
			return cname;
		}

		public void setCname(String cname) {
			this.cname = cname;
		}

		public CharSequence creatInfo() {
			StringBuffer sb = new StringBuffer();
			sb.append("By ");
			sb.append(username);
			sb.append(".");
			sb.append(Utils.convertTime(Long.parseLong(addtime)));
			sb.append(".");
			sb.append(views);
			sb.append("次阅读");
			sb.append(".");
			sb.append(comments);
			sb.append("次评论");
			return sb.toString();
		}
	}
}
