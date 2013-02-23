
package jp.ddo.trismegistos.niconicoseigaviwer.activity;

import jp.ddo.trismegistos.androidutil.view.WebImageView;
import jp.ddo.trismegistos.androidutil.view.helper.WebImageLoader;
import jp.ddo.trismegistos.niconicoseigaviwer.R;
import jp.ddo.trismegistos.niconicoseigaviwer.constant.WebUrls;
import jp.ddo.trismegistos.niconicoseigaviwer.util.ImageUtil;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;

/**
 * 大きいサイズの画像を表示する。
 * 
 * @author y_sugasawa
 * @since 2013/02/01
 */
public class DispActivity extends SherlockFragmentActivity implements LoaderCallbacks<Bitmap> {

    public static final String ARGS_IMG_TITLE = "imgTitle";
    public static final String ARGS_IMG_ID = "imgId";

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_disp);

        final Intent intent = getIntent();
        final String title = intent.getStringExtra(ARGS_IMG_TITLE);
        final String imageId = intent.getStringExtra(ARGS_IMG_ID);
        ((TextView) findViewById(R.id.title)).setText(title);
        final WebImageView view = (WebImageView) findViewById(R.id.large_image);
        view.setUrl(ImageUtil.getLargeImg(imageId));
        view.setScalable(true);
        view.setLongClickable(true);
        view.setOnLongClickListener(createClickListener(imageId));
        view.show();
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * 画像ロングタップ時のリスナーを作成する。
     * 
     * @param imageId 画像ID
     * @return 画像ロングタップ時リスナー
     */
    public OnLongClickListener createClickListener(final String imageId) {
        return new OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                final DialogFragment dialogFragment = new ImageTapDialog();
                final Bundle args = new Bundle();
                args.putString(ARGS_IMG_ID, imageId);
                dialogFragment.setArguments(args);
                dialogFragment.show(getSupportFragmentManager(), "dialog");
                return false;
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Loader<Bitmap> onCreateLoader(final int id, final Bundle args) {
        final String imageId = args.getString(ARGS_IMG_ID);
        return new WebImageLoader(getApplicationContext(), ImageUtil.getLargeImg(imageId),
                ImageUtil.getAppFileDir(getApplicationContext()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoadFinished(final Loader<Bitmap> loader, final Bitmap data) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoaderReset(final Loader<Bitmap> loader) {
    }

    public static class ImageTapDialog extends DialogFragment {

        public Dialog onCreateDialog(final Bundle savedInstanceState) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final String imageId = getArguments().getString(ARGS_IMG_ID);
            builder.setItems(R.array.image_menus, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialog, final int which) {
                    final Bundle args = new Bundle();
                    args.putString(ARGS_IMG_ID, imageId);
                    switch (which) {
                        case 0:
                            getActivity().getSupportLoaderManager().initLoader(0, args,
                                    (LoaderCallbacks<Bitmap>) getActivity());
                            break;
                        case 1:
                            final Uri uri = Uri.parse(WebUrls.OFFICAL_URL + imageId);
                            final Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;
                        default:
                            break;
                    }
                    dialog.dismiss();
                }
            });
            return builder.create();
        }
    }
}
