
package jp.ddo.trismegistos.nicopic.activity;

import jp.ddo.trismegistos.androidutil.file.FileUtil;
import jp.ddo.trismegistos.nicopic.R;
import jp.ddo.trismegistos.nicopic.util.ImageUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

/**
 * スプラッシュ画面表示。
 * 
 * @author y_sugasawa
 * @since 2013/01/24
 */
public class SplashActivity extends Activity {

    /** スプラッシュ表示時間 */
    private static final int DELAY_TIME = 2000;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // ファイルキャッシュ削除
                FileUtil.deleteAll(ImageUtil.getCacheDir(getApplicationContext()));
                final Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, DELAY_TIME);
    }
}
