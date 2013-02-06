package jp.ddo.trismegistos.niconicoseigaviwer.loader;

import jp.ddo.trismegistos.androidhttp.api.DefaultApi;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

/**
 * @author y_sugasawa
 * @since 2013/01/27
 */
public class SampleTask extends AsyncTaskLoader<String> {

	/**
	 * @param context
	 */
	public SampleTask(Context context) {
		super(context);
		Log.e("a", "con");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String loadInBackground() {
		Log.e("a", "hoge");
		DefaultApi api = new DefaultApi("http://www.yahoo.co.jp/");
		//Log.e("a", api.get());
		return null;
	}

}
