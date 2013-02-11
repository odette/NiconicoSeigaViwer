package jp.ddo.trismegistos.niconicoseigaviwer.activity;

import jp.ddo.trismegistos.niconicoseigaviwer.R;
import jp.ddo.trismegistos.niconicoseigaviwer.fragment.RankingFragment;
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

	/** カテゴリのIDリスト。 */
	private static int[] CATEGORY_IDS = { R.id.menu_category_all, R.id.menu_category_anime,
			R.id.menu_category_character, R.id.menu_category_creation, R.id.menu_category_fanart,
			R.id.menu_category_game, R.id.menu_category_orignal, R.id.menu_category_popular,
			R.id.menu_category_portrait, R.id.menu_category_r15, R.id.menu_category_vocaloid,
			R.id.menu_category_toho };

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

		// 初期表示時は総合ランキング
		actionBar.setTitle(R.string.category_all_disp);
		dispRanking(getString(R.string.category_all));
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
	 */
	private void dispRanking(final String category) {
		final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		Fragment f = new RankingFragment();
		final Bundle args = new Bundle();
		args.putString(RankingFragment.ARGS_CATEGORY, category);
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
		for (int i = 0; i < CATEGORY_IDS.length; i++) {
			mNav.findViewById(CATEGORY_IDS[i]).setOnClickListener(listener);
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
				int titleId = 0;
				String category = null;
				switch (v.getId()) {
					case R.id.menu_category_all:
						titleId = R.string.category_all_disp;
						category = getString(R.string.category_all);
						break;
					case R.id.menu_category_anime:
						titleId = R.string.category_anime_disp;
						category = getString(R.string.category_anime);
						break;
					case R.id.menu_category_character:
						titleId = R.string.category_character_disp;
						category = getString(R.string.category_character);
						break;
					case R.id.menu_category_creation:
						titleId = R.string.category_creation_disp;
						category = getString(R.string.category_creation);
						;
						break;
					case R.id.menu_category_fanart:
						titleId = R.string.category_fanart_disp;
						category = getString(R.string.category_fanart);
						break;
					case R.id.menu_category_game:
						titleId = R.string.category_game_disp;
						category = getString(R.string.category_game);
						break;
					case R.id.menu_category_orignal:
						titleId = R.string.category_orignal_disp;
						category = getString(R.string.category_orignal);
						break;
					case R.id.menu_category_popular:
						titleId = R.string.category_popular_disp;
						category = getString(R.string.category_popular);
						break;
					case R.id.menu_category_portrait:
						titleId = R.string.category_portrait_disp;
						category = getString(R.string.category_portrait);
						break;
					case R.id.menu_category_r15:
						titleId = R.string.category_r15_disp;
						category = getString(R.string.category_r15);
						break;
					case R.id.menu_category_vocaloid:
						titleId = R.string.category_vocaloid_disp;
						category = getString(R.string.category_vocaloid);
						break;
					case R.id.menu_category_toho:
						titleId = R.string.category_toho_disp;
						category = getString(R.string.category_toho);
						break;
					default:
						titleId = 0;
						category = null;
						break;
				}
				if (titleId != 0 && category != null) {
					actionBar.setTitle(titleId);
					mNav.close();
					dispRanking(category);
				}
			}
		};
	}
}
