package jp.ddo.trismegistos.niconicoseigaviwer.loader;

import java.util.List;

import jp.ddo.trismegistos.androidhttp.api.Api;
import jp.ddo.trismegistos.androidutil.loader.AbstractAsyncTaskLoader;
import jp.ddo.trismegistos.niconicoseigaviwer.constant.WebUrls;
import jp.ddo.trismegistos.niconicoseigaviwer.ds.dto.SeigaInfoDto;
import jp.ddo.trismegistos.niconicoseigaviwer.parser.RankingParser;
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

	/**
	 * コンストラクタ。
	 * 
	 * @param context コンテキスト
	 * @param category カテゴリー
	 */
	public RankingLoader(final Context context, final String category) {
		super(context);
		this.category = category;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<SeigaInfoDto> load() {
		final StringBuilder sb = new StringBuilder(WebUrls.RANKING);
		sb.append("fresh");
		sb.append("%2c");
		sb.append(category);
		final Api<List<SeigaInfoDto>> api = new Api<List<SeigaInfoDto>>(sb.toString(),
				new RankingParser());
		try {
			return api.get();
		} catch (Exception e) {
		}
		return null;
	}

}
