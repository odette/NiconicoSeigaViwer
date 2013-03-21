
package jp.ddo.trismegistos.nicopic.activity;

import jp.ddo.trismegistos.androidutil.view.WebImageView;
import jp.ddo.trismegistos.androidutil.view.helper.WebImageLoader;
import jp.ddo.trismegistos.nicopic.R;
import jp.ddo.trismegistos.nicopic.constant.WebUrls;
import jp.ddo.trismegistos.nicopic.util.ImageUtil;
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

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/**
 * 大きいサイズの画像を表示する。
 * 
 * @author y_sugasawa
 * @since 2013/02/01
 */
public class DispActivity extends SherlockFragmentActivity implements LoaderCallbacks<Bitmap> {

    public static final String ARGS_IMG_TITLE = "imgTitle";
    public static final String ARGS_IMG_ID = "imgId";

    private String title;
    private String imageId;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        setTheme(R.style.Theme_Sherlock_Light_DarkActionBar);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_disp);

        final Intent intent = getIntent();
        title = intent.getStringExtra(ARGS_IMG_TITLE);
        imageId = intent.getStringExtra(ARGS_IMG_ID);

        init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        menu.add("Save").setIcon(R.drawable.ic_compose)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getTitle().equals("Save")) {
            final DialogFragment dialogFragment = new ImageTapDialog();
            final Bundle args = new Bundle();
            args.putString(ARGS_IMG_ID, imageId);
            dialogFragment.setArguments(args);
            dialogFragment.show(getSupportFragmentManager(), "dialog");
        }
        return true;
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
     * {@inheritDoc}
     */
    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARGS_IMG_TITLE, title);
        outState.putString(ARGS_IMG_ID, imageId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        title = savedInstanceState.getString(ARGS_IMG_TITLE);
        imageId = savedInstanceState.getString(ARGS_IMG_ID);
        init();

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

    /**
     * 画面の初期化を行う。
     */
    private void init() {
        setTitle(title);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        final WebImageView view = (WebImageView) findViewById(R.id.large_image);
        view.setUrl(ImageUtil.getLargeImg(imageId));
        view.setScalable(true);
        view.show();
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
