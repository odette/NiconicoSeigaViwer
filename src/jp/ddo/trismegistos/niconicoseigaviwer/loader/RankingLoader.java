package jp.ddo.trismegistos.niconicoseigaviwer.loader;

import java.util.List;

import jp.ddo.trismegistos.androidhttp.api.Api;
import jp.ddo.trismegistos.androidutil.loader.AbstractAsyncTaskLoader;
import jp.ddo.trismegistos.niconicoseigaviwer.ds.dto.SeigaInfoDto;
import jp.ddo.trismegistos.niconicoseigaviwer.parser.RankingParser;
import android.content.Context;
import android.util.Log;

/**
 * @author y_sugasawa
 * @since 2013/01/30
 */
public class RankingLoader extends AbstractAsyncTaskLoader<List<SeigaInfoDto>> {

	/**
	 * @param context
	 */
	public RankingLoader(final Context context) {
		super(context);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<SeigaInfoDto> load() {
		final Api<List<SeigaInfoDto>> api = new Api<List<SeigaInfoDto>>(
				"http://ext.seiga.nicovideo.jp/api/illust/blogparts?mode=ranking&key=fresh%2call",
				new RankingParser());
		try {
			return api.get();
		} catch (Exception e) {
			Log.e("a", e.getMessage());
			Log.e("a", e.getCause().getMessage());
		}
		return null;
	}

}
