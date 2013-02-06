package jp.ddo.trismegistos.niconicoseigaviwer.activity;

import jp.ddo.trismegistos.niconicoseigaviwer.R;
import jp.ddo.trismegistos.niconicoseigaviwer.fragment.RankingFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

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

	private SimpleSideDrawer mNav;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mNav = new SimpleSideDrawer(this);
		mNav.setBehindContentView(R.layout.slide_menu);

		final ActionBar actionBar = getSupportActionBar();
		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setTitle(null);
		actionBar.setIcon(null);
		actionBar.setHomeButtonEnabled(true);

		final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		Fragment f = new RankingFragment();
		ft.replace(R.id.container, f).commit();
	}

	@Override
	public boolean onMenuItemSelected(final int featureId, final MenuItem item) {
		if (android.R.id.home == item.getItemId()) {
			mNav.toggleDrawer();
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
