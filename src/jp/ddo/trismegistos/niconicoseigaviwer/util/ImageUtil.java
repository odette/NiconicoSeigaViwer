package jp.ddo.trismegistos.niconicoseigaviwer.util;

import java.io.File;

import jp.ddo.trismegistos.androidutil.file.FileUtil;
import jp.ddo.trismegistos.niconicoseigaviwer.R;
import jp.ddo.trismegistos.niconicoseigaviwer.constant.WebUrls;
import android.content.Context;

/**
 * @author y_sugasawa
 * @since 2013/01/30
 */
public class ImageUtil {

	private ImageUtil() {
	}

	public static File getCacheDir(final Context con) {
		return new File(FileUtil.getSdCardPath() + "/" + con.getString(R.string.app_name) + "/");
	}

	public static String getLargeImg(final String id) {
		return getBase() + id + "l";
	}

	public static String getMiddleImg(final String id) {
		return getBase() + id + "m";
	}

	public static String getSmallImg(final String id) {
		return getBase() + id + "s";
	}

	private static String getBase() {
		return WebUrls.THUMBNAIL;
	}
}
