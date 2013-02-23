
package jp.ddo.trismegistos.niconicoseigaviwer.activity;

import jp.ddo.trismegistos.niconicoseigaviwer.R;
import jp.ddo.trismegistos.niconicoseigaviwer.fragment.RankingFragment;
import jp.ddo.trismegistos.niconicoseigaviwer.util.NiconicoSeigaApiUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.navdrawer.SimpleSideDrawer;

/**
 * メイン画面。
 * 
 * @author y_sugasawa
 * @since 2013/01/27
 */
public class MainActivity extends SherlockFragmentActivity {

    /** サイドメニュー。 */
    private SimpleSideDrawer mNav;

    /** アクションバー。 */
    private ActionBar actionBar;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initSideMenu();

        actionBar = getSupportActionBar();
        actionBar.setIcon(null);
        actionBar.setIcon(R.drawable.side_menu);
        actionBar.setHomeButtonEnabled(true);

        String category = null;
        String time = null;
        if (savedInstanceState != null) {
            category = savedInstanceState.getString(RankingFragment.ARGS_CATEGORY);
            time = savedInstanceState.getString(RankingFragment.ARGS_TIME);
        } else {
            category = getString(R.string.category_all);
            time = getString(R.string.time_new);
        }
        // 初期表示時は総合ランキング
        dispRanking(category, time);
    }

    public void onSaveInstanceState(final Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        RankingFragment f = (RankingFragment) getSupportFragmentManager().findFragmentById(
                R.id.container);
        savedInstanceState.putString(RankingFragment.ARGS_CATEGORY, f.getCategory());
        savedInstanceState.putString(RankingFragment.ARGS_TIME, f.getTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onMenuItemSelected(final int featureId, final MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            mNav.toggleDrawer();
        }
        return super.onMenuItemSelected(featureId, item);
    }

    /**
     * 指定したカテゴリーのランキングを表示する。
     * 
     * @param category カテゴリー
     * @param time 期間
     */
    private void dispRanking(final String category, final String time) {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        final Fragment f = new RankingFragment();
        final Bundle args = new Bundle();
        args.putString(RankingFragment.ARGS_CATEGORY, category);
        args.putString(RankingFragment.ARGS_TIME, time);
        f.setArguments(args);
        ft.replace(R.id.container, f).commit();
    }

    /**
     * サイドメニューの初期化を行う。
     */
    private void initSideMenu() {
        mNav = new SimpleSideDrawer(this);
        mNav.setBehindContentView(R.layout.slide_menu);
        mNav.setOnClickListener(createSideMenyuOnClickListener());
        final OnClickListener listener = createSideMenyuOnClickListener();
        for (int i = 0; i < NiconicoSeigaApiUtils.CATEGORY_IDS.length; i++) {
            mNav.findViewById(NiconicoSeigaApiUtils.CATEGORY_IDS[i]).setOnClickListener(listener);
        }
    }

    /**
     * サイドメニューのクリックリスナーを作成する。
     * 
     * @return サイドメニューのクリックリスナー
     */
    private OnClickListener createSideMenyuOnClickListener() {
        return new OnClickListener() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void onClick(final View v) {
                String category = NiconicoSeigaApiUtils.getCategory(getApplicationContext(),
                        v.getId());
                if (category != null) {
                    mNav.close();
                    dispRanking(category, getString(R.string.time_new));
                }
            }
        };
    }
}
