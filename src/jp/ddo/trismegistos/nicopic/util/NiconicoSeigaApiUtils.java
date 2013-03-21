
package jp.ddo.trismegistos.nicopic.util;

import jp.ddo.trismegistos.nicopic.R;
import android.content.Context;

/**
 * ニコニコ静画APIに関するUtilクラス。
 * 
 * @author y_sugasawa
 * @since 2013/02/20
 */
public class NiconicoSeigaApiUtils {

    /** カテゴリのIDリスト。 */
    public static int[] CATEGORY_IDS = {
            R.id.menu_category_all,
            R.id.menu_category_anime,
            R.id.menu_category_character,
            R.id.menu_category_creation,
            R.id.menu_category_fanart,
            R.id.menu_category_game,
            R.id.menu_category_orignal,
            R.id.menu_category_popular,
            R.id.menu_category_portrait,
            R.id.menu_category_r15,
            R.id.menu_category_vocaloid,
            R.id.menu_category_toho
    };

    /** 期間指定のDrawablesのIDリスト。 */
    public static final int[] ITEM_DRAWABLES = {
            R.drawable.new_icon,
            R.drawable.hour_icon,
            R.drawable.day_icon,
            R.drawable.week_icon,
            R.drawable.month_icon,
            R.drawable.total_icon
    };

    /**
     * 指定されたカテゴリーのリソースIDから対応するカテゴリを取得する。
     * 
     * @param con コンテキスト
     * @param resourceId リソースID
     * @return カテゴリ
     */
    public static String getCategory(final Context con, final int resourceId) {
        String category = null;
        switch (resourceId) {
            case R.id.menu_category_all:
                category = con.getString(R.string.category_all);
                break;
            case R.id.menu_category_anime:
                category = con.getString(R.string.category_anime);
                break;
            case R.id.menu_category_character:
                category = con.getString(R.string.category_character);
                break;
            case R.id.menu_category_creation:
                category = con.getString(R.string.category_creation);
                ;
                break;
            case R.id.menu_category_fanart:
                category = con.getString(R.string.category_fanart);
                break;
            case R.id.menu_category_game:
                category = con.getString(R.string.category_game);
                break;
            case R.id.menu_category_orignal:
                category = con.getString(R.string.category_orignal);
                break;
            case R.id.menu_category_popular:
                category = con.getString(R.string.category_popular);
                break;
            case R.id.menu_category_portrait:
                category = con.getString(R.string.category_portrait);
                break;
            case R.id.menu_category_r15:
                category = con.getString(R.string.category_r15);
                break;
            case R.id.menu_category_vocaloid:
                category = con.getString(R.string.category_vocaloid);
                break;
            case R.id.menu_category_toho:
                category = con.getString(R.string.category_toho);
                break;
            default:
                category = null;
                break;
        }
        return category;
    }

    /**
     * 指定された時間指定のリソースIDから対応する時間を取得する。
     * 
     * @param con コンテキスト
     * @param resourceId 時間指定のリソースID
     * @return 時間
     */
    public static String getTime(final Context con, final int resourceId) {
        String time = null;
        switch (resourceId) {
            case R.drawable.new_icon:
                time = con.getString(R.string.time_new);
                break;
            case R.drawable.hour_icon:
                time = con.getString(R.string.time_hour);
                break;
            case R.drawable.day_icon:
                time = con.getString(R.string.time_day);
                break;
            case R.drawable.week_icon:
                time = con.getString(R.string.time_week);
                break;
            case R.drawable.month_icon:
                time = con.getString(R.string.time_month);
                break;
            case R.drawable.total_icon:
                time = con.getString(R.string.time_total);
                break;
            default:
                time = null;
                break;
        }
        return time;
    }

    /**
     * カテゴリと時間からタイトルを作成する。
     * 
     * @param con コンテキスト
     * @param category カテゴリ
     * @param time 時間
     * @return タイトル
     */
    public static final String getTitle(final Context con, final String category, final String time) {
        final StringBuilder sb = new StringBuilder();
        if (category.equals(con.getString(R.string.category_all))) {
            sb.append(con.getString(R.string.category_all_disp));
        } else if (category.equals(con.getString(R.string.category_creation))) {
            sb.append(con.getString(R.string.category_creation_disp));
        } else if (category.equals(con.getString(R.string.category_orignal))) {
            sb.append(con.getString(R.string.category_orignal_disp));
        } else if (category.equals(con.getString(R.string.category_portrait))) {
            sb.append(con.getString(R.string.category_portrait_disp));
        } else if (category.equals(con.getString(R.string.category_fanart))) {
            sb.append(con.getString(R.string.category_fanart_disp));
        } else if (category.equals(con.getString(R.string.category_anime))) {
            sb.append(con.getString(R.string.category_anime_disp));
        } else if (category.equals(con.getString(R.string.category_game))) {
            sb.append(con.getString(R.string.category_game_disp));
        } else if (category.equals(con.getString(R.string.category_character))) {
            sb.append(con.getString(R.string.category_character_disp));
        } else if (category.equals(con.getString(R.string.category_popular))) {
            sb.append(con.getString(R.string.category_popular_disp));
        } else if (category.equals(con.getString(R.string.category_toho))) {
            sb.append(con.getString(R.string.category_toho_disp));
        } else if (category.equals(con.getString(R.string.category_vocaloid))) {
            sb.append(con.getString(R.string.category_vocaloid_disp));
        } else if (category.equals(con.getString(R.string.category_r15))) {
            sb.append(con.getString(R.string.category_r15_disp));
        }
        sb.append("（");
        if (time.equals(con.getString(R.string.time_new))) {
            sb.append(con.getString(R.string.time_new_disp));
        } else if (time.equals(con.getString(R.string.time_hour))) {
            sb.append(con.getString(R.string.time_hour_disp));
        } else if (time.equals(con.getString(R.string.time_day))) {
            sb.append(con.getString(R.string.time_day_disp));
        } else if (time.equals(con.getString(R.string.time_week))) {
            sb.append(con.getString(R.string.time_week_disp));
        } else if (time.equals(con.getString(R.string.time_month))) {
            sb.append(con.getString(R.string.time_month_disp));
        } else if (time.equals(con.getString(R.string.time_total))) {
            sb.append(con.getString(R.string.time_total_disp));
        }
        sb.append("）");

        return sb.toString();
    }
}
