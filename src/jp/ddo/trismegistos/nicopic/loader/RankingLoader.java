
package jp.ddo.trismegistos.nicopic.loader;

import java.util.List;

import jp.ddo.trismegistos.androidhttp.api.Api;
import jp.ddo.trismegistos.androidutil.loader.AbstractAsyncTaskLoader;
import jp.ddo.trismegistos.nicopic.constant.WebUrls;
import jp.ddo.trismegistos.nicopic.ds.dto.SeigaInfoDto;
import jp.ddo.trismegistos.nicopic.parser.RankingParser;
import android.content.Context;

/**
 * ニコニコ静画ランキング情報取得Loaderクラス。
 * 
 * @author y_sugasawa
 * @since 2013/01/30
 */
public class RankingLoader extends AbstractAsyncTaskLoader<List<SeigaInfoDto>> {

    /** カテゴリー。 */
    private String category;
    /** 取得期間。 */
    private String time;

    /**
     * コンストラクタ。
     * 
     * @param context コンテキスト
     * @param category カテゴリー
     * @param time 取得期間
     */
    public RankingLoader(final Context context, final String category, final String time) {
        super(context);
        this.category = category;
        this.time = time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<SeigaInfoDto> load() {
        final StringBuilder sb = new StringBuilder(WebUrls.RANKING);
        if (time == null) {
            sb.append("fresh");
        } else {
            sb.append(time);
        }
        sb.append("%2c");
        sb.append(category);
        final Api<List<SeigaInfoDto>> api = new Api<List<SeigaInfoDto>>(sb.toString(),
                new RankingParser());
        try {
            return api.get();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
