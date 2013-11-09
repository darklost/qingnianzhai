package com.koushikdutta.urlimageviewhelper;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * @author D.K.
 * @date 2013-6-26
 */
public class ImageUtils {

	// 获取圆角图片
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap,
			final float roundPx) {
		// Bitmap bitmap =drawableToBitamp(drawable);
		// Bitmap bitmap =((BitmapDrawable)drawable).getBitmap();
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		// final float roundPx = bitmap.getWidth() / 2;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	// 获取圆角图片
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
		return getRoundedCornerBitmap(bitmap, bitmap.getWidth() / 2);
	}

	private static Bitmap drawableToBitamp(Drawable drawable) {
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();
		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		// 注意，下面三行代码要用到，否在在View或者surfaceview里的canvas.drawBitmap会看不到图
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		drawable.draw(canvas);
		return bitmap;
	}
}
