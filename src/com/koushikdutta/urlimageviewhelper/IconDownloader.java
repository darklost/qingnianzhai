package com.koushikdutta.urlimageviewhelper;

import java.io.InputStream;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

public class IconDownloader implements UrlDownloader {
	@Override
	public void download(final Context context, final String url,
			final String filename, final UrlDownloaderCallback callback,
			final Runnable completion) {
		final AsyncTask<Void, Void, Void> downloader = new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(final Void... params) {
				try {
					final String packageName = url.substring(5);
					PackageManager pm = context.getPackageManager();
					Drawable icon = pm.getApplicationIcon(packageName);
					InputStream is = FormatTools.getInstance()
							.Drawable2InputStream(icon);
					callback.onDownloadComplete(IconDownloader.this, is, null);
					return null;
				} catch (final Throwable e) {
					e.printStackTrace();
					return null;
				}
			}

			@Override
			protected void onPostExecute(final Void result) {
				completion.run();
			}
		};

		UrlImageViewHelper.executeTask(downloader);
	}

	@Override
	public boolean allowCache() {
		return false;
	}

	@Override
	public boolean canDownloadUrl(String url) {
		return url.startsWith("apk:/");
	}
}
