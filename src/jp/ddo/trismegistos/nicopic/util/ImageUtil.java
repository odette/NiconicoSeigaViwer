
package jp.ddo.trismegistos.nicopic.util;

import java.io.File;

import jp.ddo.trismegistos.androidutil.file.FileUtil;
import jp.ddo.trismegistos.nicopic.R;
import jp.ddo.trismegistos.nicopic.constant.WebUrls;
import android.content.Context;

/**
 * イメージに関するUtilクラス。
 * 
 * @author y_sugasawa
 * @since 2013/01/30
 */
public class ImageUtil {

    /**
     * プライベートコンストラクタ。
     */
    private ImageUtil() {
    }

    /**
     * アプリで使用するディレクトリを取得する。
     * 
     * @param con コンテキスト
     * @return ディレクトリ
     */
    public static File getAppFileDir(final Context con) {
        return new File(FileUtil.getSdCardPath() + FileUtil.FILE_SEPARATOR
                + con.getString(R.string.app_name) + FileUtil.FILE_SEPARATOR);
    }

    /**
     * ファイルキャッシュ用のディレクトリを取得する。
     * 
     * @param con コンテキスト
     * @return ファイルキャッシュ用ディレクトリ
     */
    public static File getCacheDir(final Context con) {
        return new File(getAppFileDir(con).getAbsolutePath() + FileUtil.FILE_SEPARATOR + "cache"
                + FileUtil.FILE_SEPARATOR);
    }

    /**
     * スモールサイズの画像URLを取得する。
     * 
     * @param id 画像ID
     * @return スモールサイズの画像URL
     */
    public static String getLargeImg(final String id) {
        return getBase() + id + "l";
    }

    /**
     * ミドルサイズの画像URLを取得する。
     * 
     * @param id 画像ID
     * @return ミドルサイズの画像URL
     */
    public static String getMiddleImg(final String id) {
        return getBase() + id + "m";
    }

    /**
     * ラージサイズの画像URLを取得する。
     * 
     * @param id 画像ID
     * @return ラージサイズの画像URL
     */
    public static String getSmallImg(final String id) {
        return getBase() + id + "s";
    }

    /**
     * 画像URLのベースを取得する。
     * 
     * @return 画像URLのベース
     */
    private static String getBase() {
        return WebUrls.THUMBNAIL;
    }
}
