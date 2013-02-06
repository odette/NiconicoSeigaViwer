package jp.ddo.trismegistos.niconicoseigaviwer.activity;

import jp.ddo.trismegistos.androidutil.ui.WebImageView;
import jp.ddo.trismegistos.niconicoseigaviwer.R;
import jp.ddo.trismegistos.niconicoseigaviwer.util.ImageUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;

/**
 * @author y_sugasawa
 * @since 2013/02/01
 */
public class DispActivity extends SherlockFragmentActivity {

	public static final String ARGS_IMG_TITLE = "imgTitle";
	public static final String ARGS_IMG_ID = "imgId";

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_disp);

		final Intent intent = getIntent();
		((TextView) findViewById(R.id.title)).setText(intent.getStringExtra(ARGS_IMG_TITLE));
		final WebImageView view = (WebImageView) findViewById(R.id.large_image);
		view.setUrl(ImageUtil.getLargeImg(intent.getStringExtra(ARGS_IMG_ID)));
		view.draw();
	}

	@Override
	public boolean onKeyDown(final int keyCode, final KeyEvent event) {
		if (keyCode != KeyEvent.KEYCODE_BACK) {
			return super.onKeyDown(keyCode, event);
		} else {
			finish();
			overridePendingTransition(R.anim.close_enter, R.anim.close_exit);
			return true;
		}
	}
}
